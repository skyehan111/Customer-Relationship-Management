package utils;

import java.util.UUID;

/*
文件上传的工具类
 */
public class UploadUtils {
    //随机文件名
    public static String getUuidFileName(String fileName){
        int idx = fileName.lastIndexOf(".");
        String extions = fileName.substring(idx);
        return UUID.randomUUID().toString().replace("-"," " );
    }

    //目录分离
    public static String getPath(String uuidFileName){
        int code1 = uuidFileName.hashCode();
        int d1 = code1 & 0xf;
        int code2 = code1 >>> 4;
        int d2 = code2 & 0xf;
        return "/"+d1+"/"+d2;
    }
}
