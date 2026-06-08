package com.himanshukori.onlinejudge.judge.executor;

import com.himanshukori.onlinejudge.judge.dto.ExecutionResult;
import com.himanshukori.onlinejudge.judge.dto.ExecutionStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class JavaExecutor implements CodeExecutor {

    private static final String TEMP_DIR = "temp";

    @Override
    public ExecutionResult execute(
            String sourceCode,
            String input
    ) {

        try {

            Files.createDirectories(
                    Path.of(TEMP_DIR)
            );

            Path sourceFile =
                    Path.of(
                            TEMP_DIR,
                            "Main.java"
                    );

            Files.writeString(
                    sourceFile,
                    sourceCode
            );

            Process compileProcess =
                    new ProcessBuilder(
                            "javac",
                            sourceFile.toString()
                    )
                    .redirectErrorStream(true)
                    .start();

            String compileOutput =
                    new String(
                            compileProcess
                                    .getInputStream()
                                    .readAllBytes()
                    );

            int compileExitCode =
                    compileProcess.waitFor();

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

            Process runProcess =
                    new ProcessBuilder(
                            "java",
                            "-cp",
                            TEMP_DIR,
                            "Main"
                    )
                    .redirectErrorStream(true)
                    .start();

            try (
                    BufferedWriter writer =
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            runProcess.getOutputStream()
                                    )
                            )
            ) {

                writer.write(input);
                writer.newLine();
                writer.flush();
            }

            String output =
                    new String(
                            runProcess
                                    .getInputStream()
                                    .readAllBytes()
                    );

            int runExitCode =
                    runProcess.waitFor();

            if (runExitCode != 0) {

                return ExecutionResult.builder()
                        .status(
                                ExecutionStatus.RUNTIME_ERROR
                        )
                        .error(output)
                        .build();
            }

            return ExecutionResult.builder()
                    .status(
                            ExecutionStatus.SUCCESS
                    )
                    .output(output.trim())
                    .build();

        } catch (Exception e) {

            return ExecutionResult.builder()
                    .status(
                            ExecutionStatus.RUNTIME_ERROR
                    )
                    .error(e.getMessage())
                    .build();
        }
    }
}