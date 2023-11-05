package com.program.quizapp.controller;


import com.program.quizapp.model.Player;
import com.program.quizapp.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public Player addPlayer( @RequestBody Player player) {
      return  playerService.savePlayer(player);
    }
    @RequestMapping(value = "/{player_id}")
    public Optional<Player> getPlayer(@PathVariable Long player_id) {
        return playerService.getPlayer(player_id);
    }

    @RequestMapping(method = {GET}, path = "/all")
    public List<Player>  getAllPlayers(){
        return playerService.getAllPlayers();
    }
    @RequestMapping(value = "/quiz/{source_id}/{quiz_id}/{target_id}", method=RequestMethod.POST)
    public void copyQuiz(@PathVariable Long source_id,@PathVariable Long quiz_id,@PathVariable Long target_id){
        playerService.copyQuizToTarget(source_id,quiz_id,target_id);
    }
}
