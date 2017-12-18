package com.ani.ccyl.leg.persistence.mapper;

import com.ani.ccyl.leg.persistence.mapper.base.SysMapper;
import com.ani.ccyl.leg.persistence.po.QuestionPO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
public interface QuestionMapper extends SysMapper<QuestionPO> {
    List<QuestionPO> findDayQuestions(Timestamp createTime);
    List<QuestionPO> findTopThree();
}
