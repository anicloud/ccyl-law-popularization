package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.DayQuestionPO;
import com.ani.ccyl.leg.persistence.po.QuestionPO;

/**
 * Created by lihui on 17-12-18.
 */
public interface DayQuestionMapper extends SysMapper<DayQuestionPO> {
    QuestionPO findNewQuestion(Integer order);
}
