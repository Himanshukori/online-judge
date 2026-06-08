package com.himanshukori.onlinejudge.judge.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecutionResult {

    private ExecutionStatus status;

    private String output;

    private String error;
}