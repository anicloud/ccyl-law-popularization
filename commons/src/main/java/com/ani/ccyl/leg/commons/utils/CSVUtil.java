package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.dto.CsvDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;

        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    public List<String> getCsvStrings(List<CsvDto> csvDtos) {
        List<String> csvStrs = new ArrayList<>();
        if(csvDtos != null) {
            for(CsvDto csvDto:csvDtos) {
                String str = csvDto.getNickName()+","+csvDto.getName()+","+csvDto.getProvince()+","+csvDto.getProvinceOrder()+","+csvDto.getAge()+","+csvDto.getEmail()+","+csvDto.getPhone()+","+csvDto.getOrgName()+","+csvDto.getSex();
                csvStrs.add(str);
            }
        }
        return csvStrs;
    }
}
