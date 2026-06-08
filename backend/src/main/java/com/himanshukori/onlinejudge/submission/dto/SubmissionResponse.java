package com.himanshukori.onlinejudge.submission.dto;

import com.himanshukori.onlinejudge.submission.entity.SubmissionStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponse {

    private Long id;

    private SubmissionStatus status;

    private Integer passedTestCases;

    private Integer totalTestCases;
}