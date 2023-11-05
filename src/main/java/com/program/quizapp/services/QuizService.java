package com.program.quizapp.services;

import com.program.quizapp.model.*;
import com.program.quizapp.repository.PlayerRepo;
import com.program.quizapp.repository.QuestionRepo;
import com.program.quizapp.repository.QuizRepo;
import com.program.quizapp.repository.QuizResultRepo;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuizResultRepo quizResultRepo;

    public List<Quiz> allQuizByPlayer(Long playerId){
        Optional<Player> playerOptional = playerRepo.findById(playerId);
        if(playerOptional.isPresent())
            return playerOptional.orElseThrow().getQuizzes();
        return null;
    }

    public Quiz createQuiz(Quiz quiz){
        List<Quiz> quizzes = quizRepo.findByquizName(quiz.getQuizName());
        for(Quiz quiz1: quizzes){
            if(quiz1.getQuizName().equals(quiz.getQuizName()))return null;
        }
        return quizRepo.save(quiz);
    }

    public Quiz createQuizForPlayerWithQuiz(Long playerId,Quiz quiz){
        Optional<Player> playerOptional = playerRepo.findById(playerId);
        for (Quiz quiz1 : playerOptional.get().getQuizzes()){
            if(quiz1.equals(quiz))return null;
        }
        Player player = playerOptional.get();
        quiz.getPlayers().add(player);
        quizRepo.save(quiz);
        playerRepo.save(player);
        return quiz;
    }

    public Quiz createQuizForPlayerByQuizId(Long playerId,Long quizId){
        Player player = playerRepo.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("player not found with id: " + playerId));
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));
        for (Player player1 : quiz.getPlayers()){
            if(player1.getId().equals(playerId))return null;
        }
        quiz.getPlayers().add(player);
        quizRepo.save(quiz);
        playerRepo.save(player);
        return quiz;
    }

    public List<Quiz> getQuizOfPlayer(Long player_id){
        Optional<Player> playerOptional = playerRepo.findById(player_id);
        return playerOptional.map(Player::getQuizzes).orElse(null);
    }



    public void createQuestionForQuiz(Long quizId,Question question){
        Optional<Quiz> quizOptional = quizRepo.findById(quizId);
        if(quizOptional.isPresent()){
            Quiz quiz= quizOptional.get();
            question.getQuizzes().add(quiz);
            questionRepo.save(question);
            quizRepo.save(quiz);
        }
    }

    public List<Question> getQuestionByQuizBPlayer(Long playerId,Long quizId){
        Player player = playerRepo.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("player not found with id: " + playerId));
        List<Quiz> quizzes = player.getQuizzes();
        for(Quiz quiz : quizzes){
            if(quiz.getId().equals(quizId)){
                return quiz.getQuestions();
            }
        }
        return new ArrayList<>();
    }

    public Quiz addQuestionToQuizById(Long quizId, Long questionId){
        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Ques not found with id: " + questionId));
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));

        question.getQuizzes().add(quiz);
        questionRepo.save(question);
        quizRepo.save(quiz);
        return quiz;
    }
    public List<Question> getAllQuestionOfQuiz(Long quizId){
        Optional<Quiz> quizOptional = quizRepo.findById(quizId);
        return quizOptional.map(Quiz::getQuestions).orElse(null);
    }

    public QuizResult calculateQuizResult(Long playerId, Long quizId, @NotNull List<QuizAnswerDao> quizAnswerDao) {
        Player player = playerRepo.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("player not found with id: " + playerId));
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));
        long totalScore =0;
        for(QuizAnswerDao quizAnswerDao1 : quizAnswerDao){
            Question question = questionRepo.findById(quizAnswerDao1.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + quizAnswerDao1.getQuestionId()));
            List<Boolean> answerList = question.getIs_correct();
            if(answerList.equals(quizAnswerDao1.getOptionlist())){
                totalScore+=1;
            }
        }
        List<QuizResult> QuizResults = quizResultRepo.findAll();
        if(!QuizResults.isEmpty())
        {
            for (QuizResult quizResult1 : QuizResults) {
                if (quizResult1.getPlayer().equals(player) && quizResult1.getQuiz().equals(quiz)) {
                    quizResult1.setScore(totalScore);
                    quizResultRepo.save(quizResult1);
                    return quizResult1;
                }
            }
        }
        QuizResult quizResult1 =new QuizResult();
        quizResult1.setQuiz(quiz);
        quizResult1.setPlayer(player);
        quizResult1.setScore(totalScore);
        quizResultRepo.save(quizResult1);
        return quizResult1;
    }
}
