package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.FileDto;
import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.utils.DateUtil;
import com.ani.ccyl.leg.commons.utils.ExcelUtil;
import com.ani.ccyl.leg.commons.utils.FileUtil;
import com.ani.ccyl.leg.persistence.mapper.DayQuestionMapper;
import com.ani.ccyl.leg.persistence.mapper.FileMapper;
import com.ani.ccyl.leg.persistence.mapper.QuestionMapper;
import com.ani.ccyl.leg.persistence.mapper.ScoreRecordMapper;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
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
    public List<QuestionDto> findDayQuestions(Integer accountId) throws ParseException {
        List<QuestionPO> dayQuestions = questionMapper.findDayQuestions(new Timestamp(System.currentTimeMillis()));
        List<QuestionDto> resultDtos = new ArrayList<>();
        if(dayQuestions == null || dayQuestions.size()==0) {
            dayQuestions = questionMapper.findTopThree();
            if(dayQuestions != null) {
                int i = 1;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startTime = simpleDateFormat.parse(Constants.PROPERTIES.getProperty("start.time"));
                Integer dayNum = DateUtil.differentDays(startTime,new Date())+1;
                for(QuestionPO questionPO:dayQuestions) {
                    DayQuestionPO dayQuestionPO = new DayQuestionPO(null,dayNum,null,new Timestamp(System.currentTimeMillis()),false, i);
                    QuestionDto questionDto = QuestionAdapter.fromPO(questionPO);
                    questionDto.setOrder(i);
                    questionDto.setDayNum(dayQuestionPO.getDayNum());
                    resultDtos.add(questionDto);
                    dayQuestionMapper.insertSelective(dayQuestionPO);
                    i++;
                }
            }
        } else {
            for(QuestionPO questionPO:dayQuestions) {
                QuestionDto questionDto = QuestionAdapter.fromPO(questionPO);
                DayQuestionPO dayQuestionPO = dayQuestionMapper.selectByPrimaryKey(questionPO.getId());
                questionDto.setOrder(dayQuestionPO.getOrderNum());
                questionDto.setDayNum(dayQuestionPO.getDayNum() == null?1:dayQuestionPO.getDayNum());
                resultDtos.add(questionDto);
            }
        }
        return resultDtos;
    }

    @Override
    public QuestionDto updateNewQuestion(Integer accountId) throws ParseException {
        QuestionDto questionDto = QuestionAdapter.fromPO(scoreRecordMapper.findCurrentQuestion(accountId));
        if(questionDto == null) {
            List<QuestionDto> questionDtos = findDayQuestions(accountId);
            if(questionDtos != null && questionDtos.size()>0)
                questionDto = questionDtos.get(0);
        } else {
            DayQuestionPO dayQuestionPO = dayQuestionMapper.selectByPrimaryKey(questionDto.getId());
            Integer order = dayQuestionPO.getOrderNum();
            if(order<3) {
                questionDto = QuestionAdapter.fromPO(dayQuestionMapper.findNewQuestion(order+1));
                questionDto.setDayNum(dayQuestionPO.getDayNum());
                questionDto.setOrder(order+1);
            } else {
                return null;
            }
        }
        return questionDto;
    }
}
