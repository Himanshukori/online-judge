package com.himanshukori.onlinejudge.testcase.controller;

import com.himanshukori.onlinejudge.testcase.dto.TestCaseRequest;
import com.himanshukori.onlinejudge.testcase.entity.TestCase;
import com.himanshukori.onlinejudge.testcase.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testcases")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @PostMapping
    public TestCase addTestCase(
            @RequestBody TestCaseRequest request
    ) {
        return testCaseService.addTestCase(
                request
        );
    }
}