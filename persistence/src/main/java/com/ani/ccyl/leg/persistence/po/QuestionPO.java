package com.ani.ccyl.leg.persistence.po;

import com.ani.ccyl.leg.commons.enums.CategoryEnum;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lihui on 17-12-12.
 */
@Table(name = "t_question")
public class QuestionPO implements Serializable{
    private static final long serialVersionUID = 4977445732454556437L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
    private Integer id;
    private String title;
    private CategoryEnum category;
    private String content;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private QuestionTypeEnum type;
    private String answer;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Boolean isDel;
    private Integer questionNo;
    private Integer fileId;

    public QuestionPO(Integer id, String title, CategoryEnum category, String content, String optionOne, String optionTwo, String optionThree, QuestionTypeEnum type, String answer, Timestamp updateTime, Timestamp createTime, Boolean isDel, Integer questionNo, Integer fileId) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.type = type;
        this.answer = answer;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDel = isDel;
        this.questionNo = questionNo;
        this.fileId = fileId;
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

    public QuestionPO() {
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }
}
