package com.program.quizapp.repository;

import com.program.quizapp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Long> {
    List<Player> getByplayerName(String playerName);
}

