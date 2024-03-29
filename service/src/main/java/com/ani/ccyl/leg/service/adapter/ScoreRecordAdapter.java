package com.ani.ccyl.leg.service.adapter;

import com.ani.ccyl.leg.commons.dto.AccountDto;
import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.dto.ScoreRecordDto;
import com.ani.ccyl.leg.persistence.po.ScoreRecordPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihui on 17-12-18.
 */
public class ScoreRecordAdapter {
    public static ScoreRecordDto fromPO(ScoreRecordPO scoreRecordPO) {
        if(scoreRecordPO != null) {
            AccountDto accountDto = new AccountDto();
            QuestionDto questionDto = new QuestionDto();
            accountDto.setId(scoreRecordPO.getSrcAccountId());
            questionDto.setId(scoreRecordPO.getSrcQuestionId());
            return new ScoreRecordDto(
                    scoreRecordPO.getId(),
                    scoreRecordPO.getAccountId(),
                    scoreRecordPO.getScore(),
                    scoreRecordPO.getSrcType(),
                    questionDto,
                    scoreRecordPO.getSelfAnswer(),
                    accountDto
            );
        }
        return null;
    }
    public static List<ScoreRecordDto> fromPOList(List<ScoreRecordPO> scoreRecordPOs) {
        if(scoreRecordPOs != null) {
            List<ScoreRecordDto> scoreRecordDtos = new ArrayList<>();
            for(ScoreRecordPO scoreRecordPO:scoreRecordPOs) {
                scoreRecordDtos.add(fromPO(scoreRecordPO));
            }
            return scoreRecordDtos;
        }
        return null;
    }
}
