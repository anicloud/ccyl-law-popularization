package com.ani.ccyl.leg.commons.dto;

import com.ani.ccyl.leg.commons.enums.CategoryEnum;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-13.
 */
public class QuestionDto implements Serializable {
    private static final long serialVersionUID = 8489051395234397897L;
    private Integer id;
    private String title;
    private CategoryEnum category;
    private String content;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private QuestionTypeEnum type;
    @JsonIgnore
    private String answer;
    private Integer questionNo;
    private Integer fileId;
    private Integer order;
    private Integer dayNum;

    public QuestionDto() {
    }

    public QuestionDto(Integer id, String title, CategoryEnum category, String content, String optionOne, String optionTwo, String optionThree, QuestionTypeEnum type, String answer, Integer questionNo, Integer fileId, Integer order, Integer dayNum) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.type = type;
        this.answer = answer;
        this.questionNo = questionNo;
        this.fileId = fileId;
        this.order = order;
        this.dayNum = dayNum;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Integer questionNo) {
        this.questionNo = questionNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public QuestionTypeEnum getType() {
        return type;
    }

    public void setType(QuestionTypeEnum type) {
        this.type = type;
    }

    @JsonIgnore
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
