package com.program.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String questionText;

    private ArrayList<String> options;

    private ArrayList<Boolean> is_correct;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )
    private List<Quiz> quizzes;

    public List<Quiz> getQuizzes() {
        if (quizzes == null) {
            quizzes = new ArrayList<>();
        }
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public Question() {
        super();
    }

    public Long getId() {
        return id;
    }

    public ArrayList<Boolean> getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(ArrayList<Boolean> is_correct) {
        this.is_correct = is_correct;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

//    public List<Quiz> getQuizzes() {
//      if (quizzes == null) {
//            quizzes = new ArrayList<>();
//        }
//        return quizzes;
//    }
//
//    public void setQuizzes(List<Quiz> quizzes) {
//        this.quizzes = quizzes;
//    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

//    public List<Integer> getCorrectOptionIndex() {
//        return correctOptionIndex;
//    }
//
//    public void setCorrectOptionIndex(ArrayList<Integer> correctOptionIndex) {
//        this.correctOptionIndex = correctOptionIndex;
//    }
}
