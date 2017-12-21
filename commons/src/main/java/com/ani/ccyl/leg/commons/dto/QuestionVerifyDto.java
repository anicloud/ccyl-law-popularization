package com.ani.ccyl.leg.commons.dto;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-15.
 */
public class QuestionVerifyDto implements Serializable {
    private static final long serialVersionUID = 621548080400021512L;
    private Boolean isCorrect;
    private String answer;

    public QuestionVerifyDto() {
    }

    public QuestionVerifyDto(Boolean isCorrect, String answer) {
        this.isCorrect = isCorrect;
        this.answer = answer;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
