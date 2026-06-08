package com.himanshukori.onlinejudge.submission.service;

import com.himanshukori.onlinejudge.judge.dto.JudgeResult;
import com.himanshukori.onlinejudge.judge.service.JudgeService;
import com.himanshukori.onlinejudge.problem.entity.Problem;
import com.himanshukori.onlinejudge.problem.repository.ProblemRepository;
import com.himanshukori.onlinejudge.submission.dto.SubmissionRequest;
import com.himanshukori.onlinejudge.submission.entity.Submission;
import com.himanshukori.onlinejudge.submission.repository.SubmissionRepository;
import com.himanshukori.onlinejudge.user.entity.User;
import com.himanshukori.onlinejudge.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final JudgeService judgeService;

    public Submission submit(
            SubmissionRequest request,
            String email
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

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );

        JudgeResult judgeResult =
                judgeService.judgeSubmission(
                        request.getProblemId(),
                        request.getSourceCode(),
                        request.getLanguage()
                );

        Submission submission =
                Submission.builder()
                        .sourceCode(
                                request.getSourceCode()
                        )
                        .language(
                                request.getLanguage()
                        )
                        .status(
                                judgeResult.getStatus()
                        )
                        .passedTestCases(
                                judgeResult.getPassed()
                        )
                        .totalTestCases(
                                judgeResult.getTotal()
                        )
                        .submittedAt(
                                LocalDateTime.now()
                        )
                        .problem(problem)
                        .user(user)
                        .build();

        return submissionRepository.save(
                submission
        );
    }

    public List<Submission> getMySubmissions(
            String email
    ) {
        return submissionRepository
                .findByUserEmail(email);
    }

    public Submission getSubmissionById(
            Long submissionId
    ) {
        return submissionRepository
                .findById(submissionId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Submission not found"
                        )
                );
    }
}