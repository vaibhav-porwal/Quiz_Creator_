package com.program.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;


import java.util.List;

@Getter
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UserType userType;
    private String playerName;

    @JsonIgnore
    @ManyToMany(mappedBy = "players")
    private List<Quiz> quizzes;

    public Long getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public Player() {
       super();
    }

    public Player(Long id, UserType userType, String playerName, List<Quiz> quizzes) {
        this.id = id;
        this.userType = userType;
        this.playerName = playerName;
        this.quizzes = quizzes;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", userType=" + userType +
                ", playerName='" + playerName + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}
