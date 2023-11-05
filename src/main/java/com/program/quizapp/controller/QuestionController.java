package com.program.quizapp.controller;

import com.program.quizapp.model.Question;
import com.program.quizapp.model.Quiz;
import com.program.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/{question_id}")
    public Question getQuestion(@PathVariable Long question_id){
       return questionService.getQuestion(question_id);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Question addQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

//    @RequestMapping(value = "/add/{quiz_id}",method = RequestMethod.POST )
//    public Question addQuestionToQuiz(@PathVariable Long quiz_id,@RequestBody Question question){
//        return questionService.createQuestionForQuiz(quiz_id,question);
//    }
//    @RequestMapping(value = "/add/{quiz_id}/{question_id}",method = RequestMethod.POST )
//    public Quiz addQuestionToQuizById(@PathVariable Long quiz_id, @PathVariable Long question_id){
//        return questionService.createQuizForPlayerByQuizId(quiz_id,question_id);
//    }


}
