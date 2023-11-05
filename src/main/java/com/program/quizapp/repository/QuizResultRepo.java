package com.program.quizapp.repository;

import com.program.quizapp.model.Quiz;
import com.program.quizapp.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepo extends JpaRepository<QuizResult,Long>{

}