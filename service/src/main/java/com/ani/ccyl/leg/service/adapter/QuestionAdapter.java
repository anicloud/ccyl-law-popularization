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
}
