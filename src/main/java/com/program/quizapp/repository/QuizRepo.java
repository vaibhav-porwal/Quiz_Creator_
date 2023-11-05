package com.program.quizapp.repository;

import com.program.quizapp.model.Quiz;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Long> {
    List<Quiz> findByquizName(String quizName);
}
