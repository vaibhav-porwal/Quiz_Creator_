package com.program.quizapp.services;

import com.program.quizapp.model.Player;
import com.program.quizapp.model.Quiz;
import com.program.quizapp.repository.PlayerRepo;
import com.program.quizapp.repository.QuizRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private QuizRepo quizRepo;

    public Player savePlayer(Player player){
//        List<Player> players = playerRepo.getByplayerName(player.getPlayerName());
//        for(Player player1: players){
//            if(player1.getPlayerName().equals(player.getPlayerName()))return null;
//        }
        return playerRepo.save(player);
    }

    public Optional<Player> getPlayer(Long id) {
        return playerRepo.findById(id);
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(playerRepo.findAll());
    }

    public void copyQuizToTarget(Long sourceId,Long quizId,Long targetId){
        Player sourcePlayerOptional = playerRepo.findById(sourceId)
                .orElseThrow(() -> new EntityNotFoundException("Source user not found with id: " + sourceId));

        // Find the target user
        Player targetPlayerOptional = playerRepo.findById(targetId)
                .orElseThrow(() -> new EntityNotFoundException("Target user not found with id: " + targetId));

        // Find the quiz to be copied
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));

        quiz.getPlayers().add(targetPlayerOptional);
        quizRepo.save(quiz);
        playerRepo.save(targetPlayerOptional);
    }
}
