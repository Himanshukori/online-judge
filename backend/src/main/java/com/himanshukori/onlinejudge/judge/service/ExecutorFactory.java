package com.himanshukori.onlinejudge.judge.service;

import com.himanshukori.onlinejudge.judge.executor.LocalJavaExecutor;
import com.himanshukori.onlinejudge.judge.executor.CodeExecutor;
import com.himanshukori.onlinejudge.judge.executor.DockerJavaExecutor;
import com.himanshukori.onlinejudge.submission.entity.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExecutorFactory {

    private final DockerJavaExecutor dockerJavaExecutor;

    public CodeExecutor getExecutor(
            Language language
    ) {

        return switch (language) {

            case JAVA -> dockerJavaExecutor;

            default ->
                    throw new RuntimeException(
                            "Language not supported"
                    );
        };
    }
}