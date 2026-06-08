package com.himanshukori.onlinejudge.judge.service;

import com.himanshukori.onlinejudge.judge.executor.JavaExecutor;
import com.himanshukori.onlinejudge.judge.executor.CodeExecutor;
import com.himanshukori.onlinejudge.submission.entity.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExecutorFactory {

    private final JavaExecutor javaExecutor;

    public CodeExecutor getExecutor(
            Language language
    ) {

        return switch (language) {

            case JAVA -> javaExecutor;

            default ->
                    throw new RuntimeException(
                            "Language not supported"
                    );
        };
    }
}