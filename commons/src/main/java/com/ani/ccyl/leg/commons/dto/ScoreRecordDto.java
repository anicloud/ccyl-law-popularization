package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.ScoreSrcTypeEnum;

import java.io.Serializable;

/**
 * Created by lihui on 17-12-18.
 */
public class ScoreRecordDto implements Serializable {
    private static final long serialVersionUID = -1906713816735543692L;
    private Integer id;
    private Integer accountId;
    private Integer score;
    private ScoreSrcTypeEnum srcType;
    private QuestionDto srcQuestion;
    private String selfAnswer;
    private AccountDto srcAccount;

    public ScoreRecordDto(Integer id, Integer accountId, Integer score, ScoreSrcTypeEnum srcType, QuestionDto srcQuestion, String selfAnswer, AccountDto srcAccount) {
        this.id = id;
        this.accountId = accountId;
        this.score = score;
        this.srcType = srcType;
        this.srcQuestion = srcQuestion;
        this.selfAnswer = selfAnswer;
        this.srcAccount = srcAccount;
    }

    public ScoreRecordDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ScoreSrcTypeEnum getSrcType() {
        return srcType;
    }

    public void setSrcType(ScoreSrcTypeEnum srcType) {
        this.srcType = srcType;
    }

    public QuestionDto getSrcQuestion() {
        return srcQuestion;
    }

    public void setSrcQuestion(QuestionDto srcQuestion) {
        this.srcQuestion = srcQuestion;
    }

    public String getSelfAnswer() {
        return selfAnswer;
    }

    public void setSelfAnswer(String selfAnswer) {
        this.selfAnswer = selfAnswer;
    }

    public AccountDto getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(AccountDto srcAccount) {
        this.srcAccount = srcAccount;
    }
}
