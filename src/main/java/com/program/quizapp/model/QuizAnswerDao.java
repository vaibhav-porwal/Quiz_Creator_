package com.program.quizapp.model;

import java.util.List;

public class QuizAnswerDao {

    private Long questionId;
    private List<Boolean> optionlist;

    public QuizAnswerDao() {
        super();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Boolean> getOptionlist() {
        return optionlist;
    }

    public void setOptionlist(List<Boolean> optionlist) {
        this.optionlist = optionlist;
    }
}
