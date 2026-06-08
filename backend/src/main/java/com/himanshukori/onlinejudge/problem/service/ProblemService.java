package com.himanshukori.onlinejudge.problem.service;

import com.himanshukori.onlinejudge.problem.dto.ProblemRequest;
import com.himanshukori.onlinejudge.problem.entity.Problem;
import com.himanshukori.onlinejudge.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public Problem createProblem(
            ProblemRequest request
    ) {

        Problem problem = Problem.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .difficulty(request.getDifficulty())
                .constraintsText(
                        request.getConstraintsText()
                )
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return problemRepository.save(problem);
    }
}