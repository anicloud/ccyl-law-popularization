package com.ani.ccyl.leg.service.service.impl;

import com.ani.ccyl.leg.commons.utils.CSVUtil;
import com.ani.ccyl.leg.persistence.mapper.AccountMapper;
import com.ani.ccyl.leg.persistence.mapper.Lucky20AwardsMapper;
import com.ani.ccyl.leg.persistence.mapper.Top20AwardsMapper;
import com.ani.ccyl.leg.persistence.po.AccountPO;
import com.ani.ccyl.leg.persistence.po.Lucky20AwardsPO;
import com.ani.ccyl.leg.persistence.po.Top20AwardsPO;
import com.ani.ccyl.leg.service.service.facade.AwardsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglina on 18-1-25.
 */
@Service
public class AwardsInfoServiceImpl implements AwardsInfoService {
    @Autowired
    Top20AwardsMapper top20AwardsMapper;
    @Autowired
    Lucky20AwardsMapper lucky20AwardsMapper;
    @Autowired
    AccountMapper accountMapper;@Value("${base.file.path}")
    private String baseFilePath;

    @Override
    public String getAwardsCsv(String date) throws UnsupportedEncodingException {
        List<String> values=new ArrayList<>();
        List<Top20AwardsPO> top20s=top20AwardsMapper.findByDate(date);
        values.add("*********top20*********");
        if (top20s!=null && top20s.size()!=0){
            for (Top20AwardsPO top20:top20s){
                String awardInfo=null;
                Integer accountId=top20.getAccountId();
                if (accountId!=null){
                  AccountPO account= accountMapper.selectByPrimaryKey(accountId);
                  awardInfo= URLDecoder.decode(account.getNickName(), "utf-8")+":"+top20.getType().getValue();
                }
                values.add(awardInfo);

            }
        }
        values.add("*********幸运奖*********");
        List<Lucky20AwardsPO> lucky20s=lucky20AwardsMapper.findByDate(date);
        if (lucky20s!=null && lucky20s.size()!=0){
            for (Lucky20AwardsPO lucky20:lucky20s){
                String awardInfo=null;
                Integer accountId=lucky20.getAccountId();
                if (accountId!=null){
                    AccountPO account= accountMapper.selectByPrimaryKey(accountId);
                    awardInfo=URLDecoder.decode(account.getNickName(), "utf-8")+":"+lucky20.getType().getValue();
                }
                values.add(awardInfo);
            }
        }

        String path =baseFilePath+"/top20";
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
        String resultPath=path+"/"+date+".csv";
        CSVUtil.exportCsv(resultPath,values);
        return resultPath;
    }
}
