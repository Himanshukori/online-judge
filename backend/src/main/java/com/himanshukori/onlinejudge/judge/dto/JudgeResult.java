package com.himanshukori.onlinejudge.judge.dto;

import com.himanshukori.onlinejudge.submission.entity.SubmissionStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgeResult {

    private SubmissionStatus status;

    private int passed;

    private int total;
}