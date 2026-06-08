package com.himanshukori.onlinejudge.submission.controller;
import java.util.List;

import com.himanshukori.onlinejudge.submission.dto.SubmissionRequest;
import com.himanshukori.onlinejudge.submission.entity.Submission;
import com.himanshukori.onlinejudge.submission.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public Submission submit(
            @RequestBody SubmissionRequest request,
            Authentication authentication
    ) {

        return submissionService.submit(
                request,
                authentication.getName()
        );
    }

    @GetMapping("/my")
    public List<Submission> mySubmissions(
            Authentication authentication
    ) {

        return submissionService
                .getMySubmissions(
                        authentication.getName()
                );
    }

    @GetMapping("/{id}")
    public Submission getSubmission(
            @PathVariable Long id
    ) {

        return submissionService
                .getSubmissionById(id);
    }
}