package com.himanshukori.onlinejudge.judge.service;

import com.himanshukori.onlinejudge.judge.dto.ExecutionResult;
import com.himanshukori.onlinejudge.judge.dto.ExecutionStatus;
import com.himanshukori.onlinejudge.judge.dto.JudgeResult;
import com.himanshukori.onlinejudge.judge.executor.CodeExecutor;
import com.himanshukori.onlinejudge.submission.entity.Language;
import com.himanshukori.onlinejudge.submission.entity.SubmissionStatus;
import com.himanshukori.onlinejudge.testcase.entity.TestCase;
import com.himanshukori.onlinejudge.testcase.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JudgeService {

    private final TestCaseRepository testCaseRepository;
    private final ExecutorFactory executorFactory;

    public JudgeResult judgeSubmission(
            Long problemId,
            String sourceCode,
            Language language
    ) {

        List<TestCase> testCases =
                testCaseRepository.findByProblemId(
                        problemId
                );

        int passed = 0;

        CodeExecutor executor =
                executorFactory.getExecutor(
                        language
                );

        for (TestCase testCase : testCases) {

            ExecutionResult result =
                    executor.execute(
                            sourceCode,
                            testCase.getInputData()
                    );

            if (result.getStatus()
                    == ExecutionStatus.COMPILATION_ERROR) {

                return JudgeResult.builder()
                        .status(
                                SubmissionStatus.COMPILATION_ERROR
                        )
                        .passed(passed)
                        .total(testCases.size())
                        .build();
            }

            if (result.getStatus()
                    == ExecutionStatus.RUNTIME_ERROR) {

                return JudgeResult.builder()
                        .status(
                                SubmissionStatus.RUNTIME_ERROR
                        )
                        .passed(passed)
                        .total(testCases.size())
                        .build();
            }

            String expected =
                    testCase.getExpectedOutput()
                            .trim();

            String actual =
                    result.getOutput()
                            .trim();

            if (expected.equals(actual)) {

                passed++;

            } else {

                return JudgeResult.builder()
                        .status(
                                SubmissionStatus.WRONG_ANSWER
                        )
                        .passed(passed)
                        .total(testCases.size())
                        .build();
            }
        }

        return JudgeResult.builder()
                .status(
                        SubmissionStatus.ACCEPTED
                )
                .passed(passed)
                .total(testCases.size())
                .build();
    }
}