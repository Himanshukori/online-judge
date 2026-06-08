package com.himanshukori.onlinejudge.submission.dto;

import com.himanshukori.onlinejudge.submission.entity.Language;
import lombok.Data;

@Data
public class SubmissionRequest {

    private Long problemId;

    private String sourceCode;

    private Language language;
}