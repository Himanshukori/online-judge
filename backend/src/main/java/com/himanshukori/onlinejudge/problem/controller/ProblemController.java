package com.himanshukori.onlinejudge.problem.controller;

import com.himanshukori.onlinejudge.problem.dto.ProblemRequest;
import com.himanshukori.onlinejudge.problem.entity.Problem;
import com.himanshukori.onlinejudge.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    public Problem createProblem(
            @RequestBody ProblemRequest request
    ) {

        return problemService.createProblem(
                request
        );
    }
}