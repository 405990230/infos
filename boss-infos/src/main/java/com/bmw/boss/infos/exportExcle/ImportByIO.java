package com.bmw.boss.infos.exportExcle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by qxr4383 on 2018/3/12.
 */
public class ImportByIO {
    public static void readTxtByIO(String filePath) {
        try {
            File file = new File(filePath);
            if(file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                int num =0;
                long time1 = System.currentTimeMillis();
                while ((lineTxt = br.readLine()) != null) {
                    //System.out.println(lineTxt);
                    num++;
                    //System.out.println("总共"+num+"条数据！");
                }
                System.out.println("总共"+num+"条数据！");
                long time2 = System.currentTimeMillis();
                long time = (time2-time1);
                System.out.println("共花费"+time+"ms");
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
    }

}
