package com.himanshukori.onlinejudge.problem.repository;

import com.himanshukori.onlinejudge.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository
        extends JpaRepository<Problem, Long> {
}