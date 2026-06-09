package com.himanshukori.onlinejudge.judge.executor;

import com.himanshukori.onlinejudge.judge.dto.ExecutionResult;
import com.himanshukori.onlinejudge.judge.dto.ExecutionStatus;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class DockerJavaExecutor implements CodeExecutor {

    private static final String MEMORY_LIMIT = "256m";

    private static final String CPU_LIMIT = "1";

    private static final long EXECUTION_TIMEOUT_SECONDS = 2;

    private static final long COMPILATION_TIMEOUT_SECONDS = 10;

    @Override
    public ExecutionResult execute(
            String sourceCode,
            String input
    ) {

        Path workspace = null;

        String containerName =
                "oj-" + UUID.randomUUID();

        try {

            String workspaceName =
                    "submission-" + UUID.randomUUID();

            workspace = Path.of(
                    "temp",
                    workspaceName
            );

            Files.createDirectories(workspace);

            Path sourceFile =
                    workspace.resolve("Main.java");

            Files.writeString(
                    sourceFile,
                    sourceCode
            );

            String absolutePath =
                    workspace.toAbsolutePath()
                            .toString();

            /*
             * =====================================
             * COMPILE PHASE
             * =====================================
             */

            Process compileProcess =
                    new ProcessBuilder(
                            "docker",
                            "run",
                            "--rm",
                            "--memory",
                            MEMORY_LIMIT,
                            "--cpus",
                            CPU_LIMIT,
                            "-v",
                            absolutePath + ":/app",
                            "oj-java-runner",
                            "sh",
                            "-c",
                            "cd /app && javac Main.java"
                    )
                            .redirectErrorStream(true)
                            .start();

            boolean compileFinished =
                    compileProcess.waitFor(
                            COMPILATION_TIMEOUT_SECONDS,
                            TimeUnit.SECONDS
                    );

            if (!compileFinished) {

                compileProcess.destroyForcibly();

                return ExecutionResult.builder()
                        .status(
                                ExecutionStatus.TIME_LIMIT_EXCEEDED
                        )
                        .error(
                                "Compilation timed out"
                        )
                        .build();
            }

            String compileOutput =
                    readOutput(
                            compileProcess
                    );

            int compileExitCode =
                    compileProcess.exitValue();

            if (compileExitCode != 0) {

                return ExecutionResult.builder()
                        .status(
                                ExecutionStatus.COMPILATION_ERROR
                        )
                        .error(
                                compileOutput
                        )
                        .build();
            }

            /*
             * =====================================
             * RUN PHASE
             * =====================================
             */

            String runCommand =
                    "cd /app && echo \"" +
                            input +
                            "\" | java Main";

            Process runProcess =
                    new ProcessBuilder(
                            "docker",
                            "run",
                            "--name",
                            containerName,
                            "--rm",
                            "--memory",
                            MEMORY_LIMIT,
                            "--cpus",
                            CPU_LIMIT,
                            "-v",
                            absolutePath + ":/app",
                            "oj-java-runner",
                            "sh",
                            "-c",
                            runCommand
                    )
                            .redirectErrorStream(true)
                            .start();

            boolean runFinished =
                    runProcess.waitFor(
                            EXECUTION_TIMEOUT_SECONDS,
                            TimeUnit.SECONDS
                    );

            if (!runFinished) {

                runProcess.destroyForcibly();

                try {

                    new ProcessBuilder(
                            "docker",
                            "stop",
                            containerName
                    )
                            .start()
                            .waitFor();

                } catch (Exception ignored) {
                }

                return ExecutionResult.builder()
                        .status(
                                ExecutionStatus.TIME_LIMIT_EXCEEDED
                        )
                        .error(
                                "Execution timed out"
                        )
                        .build();
            }

            String output =
                    readOutput(
                            runProcess
                    );

            int runExitCode =
                    runProcess.exitValue();

            if (runExitCode != 0) {

                return ExecutionResult.builder()
                        .status(
                                ExecutionStatus.RUNTIME_ERROR
                        )
                        .error(
                                output
                        )
                        .build();
            }

            return ExecutionResult.builder()
                    .status(
                            ExecutionStatus.SUCCESS
                    )
                    .output(
                            output.trim()
                    )
                    .build();

        } catch (Exception e) {

            return ExecutionResult.builder()
                    .status(
                            ExecutionStatus.RUNTIME_ERROR
                    )
                    .error(
                            e.getMessage()
                    )
                    .build();

        } finally {

            if (workspace != null) {

                try {

                    deleteDirectory(
                            workspace
                    );

                } catch (Exception e) {

                    System.err.println(
                            "Failed to delete workspace: "
                                    + e.getMessage()
                    );
                }
            }
        }
    }

    private String readOutput(
            Process process
    ) throws Exception {

        return new String(
                process.getInputStream()
                        .readAllBytes()
        );
    }

    private void deleteDirectory(
            Path path
    ) throws Exception {

        if (!Files.exists(path)) {
            return;
        }

        Files.walk(path)
                .sorted(
                        Comparator.reverseOrder()
                )
                .forEach(file -> {

                    try {

                        Files.delete(file);

                    } catch (Exception ignored) {
                    }
                });
    }
}