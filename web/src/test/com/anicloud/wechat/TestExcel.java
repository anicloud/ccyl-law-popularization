package com.anicloud.wechat;

import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.utils.ExcelUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
public class TestExcel {
    @Test
    public void testReadChoice() {
        List<QuestionDto> questionDtos = ExcelUtil.readFromExcel(QuestionTypeEnum.CHOICE, "/home/lihui/choice.xlsx", null);
        System.out.print(questionDtos.size());
    }

    @Test
    public void testReadJudge() {
        List<QuestionDto> questionDtos = ExcelUtil.readFromExcel(QuestionTypeEnum.JUDGEMENT,"/home/lihui/judge.xlsx",null);
        System.out.print(questionDtos.size());
    }
}
