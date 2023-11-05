package com.program.quizapp.services;

import com.program.quizapp.model.Question;
import com.program.quizapp.model.Quiz;
import com.program.quizapp.repository.QuestionRepo;
import com.program.quizapp.repository.QuizRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;

    public Question createQuestion(Question question){
        List<Question> questions = questionRepo.findAll();
        for(Question question1 : questions){
            if(question1.equals(question))return null;
        }
        return questionRepo.save(question);
    }
    public Question getQuestion(Long question_id){
        Optional<Question> questionOptional= questionRepo.findById(question_id);
        return questionOptional.orElse(null);
    }
//    public Question createQuestionForQuiz(Long quiz_id, Question question){
//        Optional<Quiz> quizOptional = quizRepo.findById(quiz_id);
//
//        if(quizOptional.isPresent()){
//            Quiz quiz= quizOptional.get();
//            question.getQuizzes().add(quiz);
//            questionRepo.save(question);
//            quizRepo.save(quiz);
//        }
//        return question;
//    }
//
//    public Quiz addQuestionToQuizById(Long quiz_id, Long question_id){
//        Question question = questionRepo.findById(question_id)
//                .orElseThrow(() -> new EntityNotFoundException("Ques not found with id: " + question_id));
//        Quiz quiz = quizRepo.findById(quiz_id)
//                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quiz_id));
//
//        question.getQuizzes().add(quiz);
//        questionRepo.save(question);
//        quizRepo.save(quiz);
//        return quiz;
//    }

}
