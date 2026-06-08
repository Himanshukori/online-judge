package com.himanshukori.onlinejudge.submission.entity;

import com.himanshukori.onlinejudge.problem.entity.Problem;
import com.himanshukori.onlinejudge.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String sourceCode;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private Integer passedTestCases;

    private Integer totalTestCases;

    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}