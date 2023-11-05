package com.program.quizapp.controller;

import com.program.quizapp.model.Question;
import com.program.quizapp.model.Quiz;
import com.program.quizapp.model.QuizAnswerDao;
import com.program.quizapp.model.QuizResult;
import com.program.quizapp.repository.QuestionRepo;
import com.program.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private QuestionRepo questionRepo;

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public Quiz addQuiz( @RequestBody Quiz quiz){
       return quizService.createQuiz(quiz);
    }


    @RequestMapping(value = "/{player_id}/add", method= RequestMethod.POST)
    public Quiz addQuizForPlayer(@PathVariable Long player_id , @RequestBody Quiz quiz){
        return quizService.createQuizForPlayerWithQuiz(player_id,quiz);
    }

    @RequestMapping(value = "/{player_id}/{quiz_id}",method = RequestMethod.POST)
    public Quiz addQuizForPlayerById(@PathVariable Long player_id ,@PathVariable Long quiz_id){
       return quizService.createQuizForPlayerByQuizId(player_id,quiz_id);
    }
    @RequestMapping(value = "/{player_id}")
    public List<Quiz> getAllQuiz(@PathVariable Long player_id){
        return quizService.getQuizOfPlayer(player_id);
    }

    @RequestMapping(value = "/{quiz_id}/questions" ,method = RequestMethod.GET)
    public List<Question>  getAllQuestionForQuiz(@PathVariable Long quiz_id){
        return quizService.getAllQuestionOfQuiz(quiz_id);
    }

    @RequestMapping(value = "/{player_id}/{quiz_id}",method = RequestMethod.GET)
    public List<Question> getAllQuestionForQuizByPlayer(@PathVariable Long player_id,@PathVariable Long quiz_id){
        return quizService.getQuestionByQuizBPlayer(player_id,quiz_id);
    }

    @RequestMapping(value = "question/add/{quiz_id}",method = RequestMethod.POST )
    public void addQuestionToQuiz(@PathVariable Long quiz_id, @RequestBody Question question){
        quizService.createQuestionForQuiz(quiz_id,question);
    }
    @RequestMapping(value = "/add/{quiz_id}/questions/{question_id}",method = RequestMethod.POST )
    public Quiz addQuestionToQuizById(@PathVariable Long quiz_id, @PathVariable Long question_id){
        return quizService.addQuestionToQuizById(quiz_id,question_id);
    }

    @RequestMapping(value = "/{player_id}/{quiz_id}/submit",method = RequestMethod.POST)
    public QuizResult calculateQuizResult(@PathVariable Long player_id, @PathVariable Long quiz_id, @RequestBody List<QuizAnswerDao> quizAnswerDao){
        return quizService.calculateQuizResult(player_id,quiz_id,quizAnswerDao);
    }
}
