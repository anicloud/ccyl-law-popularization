package com.ani.ccyl.leg.service.service.facade;

import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
public interface QuestionService {
    void insertQuestionFromFile(QuestionTypeEnum type, MultipartFile file);

    QuestionDto findById(Integer id);

    List<QuestionDto> findDayQuestions(Integer accountId);

    QuestionDto findNewQuestion(Integer accountId);
}
