package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.persistence.mapper.QuestionMapper;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lihui on 17-12-14.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public void insertQuestionFromFile(QuestionTypeEnum type, MultipartFile file) {

    }
}
