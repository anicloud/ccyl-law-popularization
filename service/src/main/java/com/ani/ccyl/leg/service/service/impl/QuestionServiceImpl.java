package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.dto.FileDto;
import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.utils.ExcelUtil;
import com.ani.ccyl.leg.commons.utils.FileUtil;
import com.ani.ccyl.leg.persistence.mapper.FileMapper;
import com.ani.ccyl.leg.persistence.mapper.QuestionMapper;
import com.ani.ccyl.leg.persistence.po.FilePO;
import com.ani.ccyl.leg.persistence.po.QuestionPO;
import com.ani.ccyl.leg.service.adapter.FileAdapter;
import com.ani.ccyl.leg.service.adapter.QuestionAdapter;
import com.ani.ccyl.leg.service.service.facade.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by lihui on 17-12-14.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
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
}
