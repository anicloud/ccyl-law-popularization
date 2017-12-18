package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.dto.FileDto;
import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.utils.ExcelUtil;
import com.ani.ccyl.leg.commons.utils.FileUtil;
import com.ani.ccyl.leg.persistence.mapper.DayQuestionMapper;
import com.ani.ccyl.leg.persistence.mapper.FileMapper;
import com.ani.ccyl.leg.persistence.mapper.QuestionMapper;
import com.ani.ccyl.leg.persistence.po.DayQuestionPO;
import com.ani.ccyl.leg.persistence.po.FilePO;
import com.ani.ccyl.leg.persistence.po.QuestionPO;
import com.ani.ccyl.leg.service.adapter.FileAdapter;
import com.ani.ccyl.leg.service.adapter.QuestionAdapter;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private DayQuestionMapper dayQuestionMapper;
    @Autowired
    private FileMapper fileMapper;
    @Override
    public void insertQuestionFromFile(QuestionTypeEnum type, MultipartFile file) {
        if(type != null && file != null) {
            FileDto fileDto = FileUtil.saveFile(file);
            FilePO filePO = FileAdapter.fromDto(fileDto);
            fileMapper.insertSelective(filePO);
            List<QuestionDto> questionDtos = ExcelUtil.readFromExcel(type, fileDto.getPath(), filePO.getId());
            List<QuestionPO> questionPOs = QuestionAdapter.fromDtoList(questionDtos);
            if(questionPOs != null) {
                for(QuestionPO questionPO:questionPOs) {
                    questionMapper.insertSelective(questionPO);
                }
            }
        }
    }

    @Override
    public QuestionDto findById(Integer id) {
        QuestionPO questionPO = questionMapper.selectByPrimaryKey(id);
        return QuestionAdapter.fromPO(questionPO);
    }

    @Override
    public List<QuestionDto> findDayQuestion() {
        List<QuestionPO> dayQuestions = questionMapper.findDayQuestions(new Timestamp(System.currentTimeMillis()));
        if(dayQuestions.size()==0) {
            dayQuestions = questionMapper.findTopThree();
            if(dayQuestions != null) {
                for(QuestionPO questionPO:dayQuestions) {
                    DayQuestionPO dayQuestionPO = new DayQuestionPO(questionPO.getId(),null,new Timestamp(System.currentTimeMillis()),false);
                    dayQuestionMapper.insertSelective(dayQuestionPO);
                }
            }
        }
        return QuestionAdapter.fromPOList(dayQuestions);
    }
}
