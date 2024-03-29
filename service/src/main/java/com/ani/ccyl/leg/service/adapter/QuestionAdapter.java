package com.ani.ccyl.leg.service.adapter;

import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.persistence.po.QuestionPO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
public class QuestionAdapter {
    public static QuestionPO fromDto(QuestionDto questionDto) {
        if(questionDto!= null)
            return new QuestionPO(
                    questionDto.getId() == null ? null:questionDto.getId(),
                    questionDto.getTitle(),
                    questionDto.getCategory(),
                    questionDto.getContent(),
                    questionDto.getOptionOne(),
                    questionDto.getOptionTwo(),
                    questionDto.getOptionThree(),
                    questionDto.getType(),
                    questionDto.getAnswer(),
                    null,
                    new Timestamp(System.currentTimeMillis()),
                    false,
                    questionDto.getQuestionNo(),
                    questionDto.getFileId()
            );
        return null;
    }
    public static List<QuestionPO> fromDtoList(List<QuestionDto> questionDtos) {
        if(questionDtos != null) {
            List<QuestionPO> questionPOs = new ArrayList<>();
            for(QuestionDto questionDto:questionDtos) {
                questionPOs.add(fromDto(questionDto));
            }
            return questionPOs;
        }
        return null;
    }

    public static QuestionDto fromPO(QuestionPO questionPO) {
        if(questionPO != null)
            return new QuestionDto(
                    questionPO.getId(),
                    questionPO.getTitle(),
                    questionPO.getCategory(),
                    questionPO.getContent(),
                    questionPO.getOptionOne(),
                    questionPO.getOptionTwo(),
                    questionPO.getOptionThree(),
                    questionPO.getType(),
                    questionPO.getAnswer(),
                    questionPO.getQuestionNo(),
                    questionPO.getFileId(),
                    null,
                    null
            );
        return null;
    }

    public static List<QuestionDto> fromPOList(List<QuestionPO> questions) {
        if(questions != null) {
            List<QuestionDto> questionDtos = new ArrayList<>();
            for(QuestionPO questionPO: questions) {
                questionDtos.add(fromPO(questionPO));
            }
            return questionDtos;
        }
        return null;
    }
}
