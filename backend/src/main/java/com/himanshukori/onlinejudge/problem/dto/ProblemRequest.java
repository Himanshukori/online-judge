package com.himanshukori.onlinejudge.problem.dto;

import com.himanshukori.onlinejudge.problem.entity.Difficulty;
import lombok.Data;

@Data
public class ProblemRequest {

    private String title;

    private String description;

    private Difficulty difficulty;

    private String constraintsText;
}