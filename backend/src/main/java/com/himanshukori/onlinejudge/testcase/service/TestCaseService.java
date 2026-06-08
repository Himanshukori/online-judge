package com.himanshukori.onlinejudge.testcase.service;

import com.himanshukori.onlinejudge.problem.entity.Problem;
import com.himanshukori.onlinejudge.problem.repository.ProblemRepository;
import com.himanshukori.onlinejudge.testcase.dto.TestCaseRequest;
import com.himanshukori.onlinejudge.testcase.entity.TestCase;
import com.himanshukori.onlinejudge.testcase.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final ProblemRepository problemRepository;

    public TestCase addTestCase(
            TestCaseRequest request
    ) {

        Problem problem =
                problemRepository.findById(
                        request.getProblemId()
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "Problem not found"
                        )
                );

        TestCase testCase =
                TestCase.builder()
                        .inputData(
                                request.getInputData()
                        )
                        .expectedOutput(
                                request.getExpectedOutput()
                        )
                        .hidden(
                                request.isHidden()
                        )
                        .problem(problem)
                        .build();

        return testCaseRepository.save(
                testCase
        );
    }
}