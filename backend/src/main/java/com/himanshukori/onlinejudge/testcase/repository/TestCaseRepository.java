package com.himanshukori.onlinejudge.testcase.repository;

import com.himanshukori.onlinejudge.testcase.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository
        extends JpaRepository<TestCase, Long> {

    List<TestCase> findByProblemId(Long problemId);
}