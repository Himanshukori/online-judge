package com.himanshukori.onlinejudge.judge.executor;

import com.himanshukori.onlinejudge.judge.dto.ExecutionResult;

public interface CodeExecutor {

    ExecutionResult execute(
            String sourceCode,
            String input
    );
}