package com.program.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quizName;

    @ManyToMany
    @JoinTable(
            name = "quiz_player",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    @JsonIgnore
    @ManyToMany(mappedBy = "quizzes")
    private List<Question> questions;

    @OneToMany(mappedBy = "quiz" ,cascade = CascadeType.ALL)
    private  List<QuizResult> quizResults;


    public Quiz() {
        super();
    }
    @Autowired
    public Quiz(Long id, String quizName, List<Player> players, List<Question> questions, List<QuizResult> quizResults) {
        this.id = id;
        this.quizName = quizName;
        this.players = players;
        //this.questions = questions;
        this.quizResults = quizResults;
    }

    public Long getId() {
        return id;
    }

    public List<Question> getQuestions() {
        if(questions ==null)return new ArrayList<>();
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public List<Player> getPlayers() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<QuizResult> getQuizResults() {
        if (quizResults == null) {
            quizResults = new ArrayList<>();
        }
        return quizResults;
    }

    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }
}
