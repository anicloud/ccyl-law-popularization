package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.dto.FileDto;
import com.ani.ccyl.leg.commons.enums.ExceptionEnum;
import com.ani.ccyl.leg.commons.enums.FileTypeEnum;
import com.ani.ccyl.leg.commons.exception.FileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihui on 17-12-14.
 */
public class FileUtil {
    private static final String BASE_FILE_PATH = Constants.PROPERTIES.getProperty("base.file.path");
    public static FileDto saveFile(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("\\."));
            String fileName = String.valueOf(System.currentTimeMillis())+ext;
            FileTypeEnum type;
            if(ext.toLowerCase().equals(".xlsx") || ext.toLowerCase().equals(".xls")) {
                type = FileTypeEnum.EXCEL;
            } else {
                throw new FileException("未知的文件类型",ExceptionEnum.FILE_TYPE_ERROR);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dirPath = BASE_FILE_PATH+ File.separator+type.name()+File.separator+simpleDateFormat.format(new Date());
            File dirFile = new File(dirPath);
            if(!dirFile.exists()) {
                boolean mkdirs = dirFile.mkdirs();
            }

            String filePath = dirPath+File.separator+fileName;
            FileOutputStream outputStream = new FileOutputStream(filePath);
            byte[] bytes = new byte[1024];
            InputStream inputStream = file.getInputStream();
            int len = 0;
            while ((len=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            inputStream.close();
            FileDto fileDto = new FileDto();
            fileDto.setType(type);
            fileDto.setPath(filePath);
            fileDto.setName(file.getName());
            return fileDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException("文件上传异常", ExceptionEnum.FILE_UPLOAD_EXCEPTION);
        }
    }
}
