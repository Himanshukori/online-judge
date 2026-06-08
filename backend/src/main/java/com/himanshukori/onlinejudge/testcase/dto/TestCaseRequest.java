package com.himanshukori.onlinejudge.testcase.dto;

import lombok.Data;

@Data
public class TestCaseRequest {

    private Long problemId;

    private String inputData;

    private String expectedOutput;

    private boolean hidden;
}