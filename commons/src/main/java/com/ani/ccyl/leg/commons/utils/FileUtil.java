package com.ani.ccyl.leg.commons.utils;

import com.ani.ccyl.leg.commons.constants.Constants;
import com.ani.ccyl.leg.commons.enums.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihui on 17-12-14.
 */
public class FileUtil {
    private static final String BASE_FILE_PATH = Constants.PROPERTIES.getProperty("base_file_path");
    public static String saveFile(MultipartFile file, FileTypeEnum type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dirPah = BASE_FILE_PATH+ File.separator+type.name()+File.separator+simpleDateFormat.format(new Date());
        File dirFile = new File(dirPah);
        if(!dirFile.exists()) {
            boolean mkdirs = dirFile.mkdirs();
        }
        return null;
    }
}
