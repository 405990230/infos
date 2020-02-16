package com.bmw.boss.infos.exportExcle.bmw;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qxr4383 on 2018/3/13.
 */
public class GetFileList {
    public static List<File> getFileList(String strPath) {
        List<File> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("txt")) { // 判断文件名是否以.avi结尾
                    //String strFileName = files[i].getAbsolutePath();
                    //System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }
}
