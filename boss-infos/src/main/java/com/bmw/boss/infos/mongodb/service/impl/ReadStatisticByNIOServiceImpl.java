package com.bmw.boss.infos.mongodb.service.impl;

import com.bmw.boss.common.util.TimeUtils;
import com.bmw.boss.infos.entity.CarRequest;
import com.bmw.boss.infos.entity.data.CarRequestData;
import com.bmw.boss.infos.mongodb.service.ICarRequestMongoService;
import com.bmw.boss.infos.mybatis.service.ICarRequestService;
import com.bmw.boss.infos.mongodb.service.ReadStatisticByNIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qxr4383 on 2018/3/12.
 */
@Service("readStatisticByNIOService")
public class ReadStatisticByNIOServiceImpl implements ReadStatisticByNIOService{
    private static Logger logger = LoggerFactory.getLogger(ReadStatisticByNIOServiceImpl.class);

    @Autowired
    ICarRequestService carRequestService;
    @Autowired
    ICarRequestMongoService carRequestMongoService;

    public void readTxtByNIOofWeather(String filePath) {
        //计数
        int  insertNum =0;
        long time1 = System.currentTimeMillis();
        FileInputStream fis = null;
        FileChannel inChannel = null;
        int bufSize = 1024*30;
        try {
            fis = new FileInputStream(filePath);
            inChannel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(bufSize);
            String enterStr = "\n";
            StringBuffer strBuf = new StringBuffer("");
            int lineNum = 0;

            while(inChannel.read(buffer) != -1){
                int rSize = buffer.position();
                buffer.clear();
                String tempString = new String(buffer.array(), 0, rSize);
                if(fis.available() ==0){//最后一行，加入"\n分割符"
                    tempString+="\n";
                }

                int fromIndex = 0;

                int endIndex = 0;
                String[] lineTxtArray = null;
                List<CarRequest> list = new ArrayList<>();
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {

                    String line = tempString.substring(fromIndex, endIndex);

                    line = new String(strBuf.toString() + line);


                    lineTxtArray = line.split("\\|");

                    //System.out.println(Arrays.toString(lineTxtArray));
                    if(lineTxtArray.length>6){
                        //System.out.println(lineTxtArray[5]);
                        //System.out.println(Arrays.toString(lineTxtArray));
                        CarRequest record = new CarRequest();
                        record.setRequestDate(TimeUtils.getDateByFormatString(lineTxtArray[0]+" "+lineTxtArray[1]));
                        record.setAppName(lineTxtArray[2]);
                        record.setPage(lineTxtArray[3]);
                        record.setPu("");
                        record.setSystem(lineTxtArray[4]);
                        record.setVin(lineTxtArray[5]);
                        record.setCreatDate(new Date());
                        list.add(record);
                        lineNum++;
                    }
                    strBuf.delete(0, strBuf.length());

                    fromIndex = endIndex + 1;

                }


                insertNum+=carRequestService.batchInsertCarRequestList(list);
                if (rSize > tempString.length()) {

                    strBuf.append(tempString.substring(fromIndex, tempString.length()));

                } else if(rSize == tempString.length()){
                    strBuf.append(tempString.substring(fromIndex, rSize));

                }
                System.out.println("lineNum ="+ lineNum+"; insertNum = "+insertNum);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取错误!");
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel != null){
                try{
                    inChannel.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            long time2 = System.currentTimeMillis();
            long time = (time2-time1)/1000;
            System.out.println("共花费"+time+"秒");
        }

    }



    public void readTxtByNIOofWeatherMongo(String filePath) {
        long time1 = System.currentTimeMillis();
        FileInputStream fis = null;
        FileChannel inChannel = null;
        int bufSize = 1024*100;
        try {
            fis = new FileInputStream(filePath);
            inChannel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(bufSize);
            String enterStr = "\n";
            StringBuffer strBuf = new StringBuffer("");
            int lineNum = 0;

            while(inChannel.read(buffer) != -1){
                int rSize = buffer.position();
                buffer.clear();
                String tempString = new String(buffer.array(), 0, rSize);
                if(fis.available() ==0){//最后一行，加入"\n分割符"
                    tempString+="\n";
                }

                int fromIndex = 0;

                int endIndex = 0;
                String[] lineTxtArray = null;
                List<CarRequestData> list = new ArrayList<>();
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {

                    String line = tempString.substring(fromIndex, endIndex);

                    line = new String(strBuf.toString() + line);


                    lineTxtArray = line.split("\\|");

                    //System.out.println(Arrays.toString(lineTxtArray));
                    if(lineTxtArray.length>6){
                        //System.out.println(lineTxtArray[5]);
                        //System.out.println(Arrays.toString(lineTxtArray));
                        CarRequestData record = new CarRequestData();
                        record.setRequestDate(TimeUtils.getDateByFormatString(lineTxtArray[0]+" "+lineTxtArray[1]));
                        record.setAppName(lineTxtArray[2]);
                        record.setPage(lineTxtArray[3]);
                        record.setPu("");
                        record.setSystem(lineTxtArray[4]);
                        record.setVin(lineTxtArray[5]);
                        record.setCreatDate(new Date());
                        list.add(record);
                        lineNum++;
                    }
                    strBuf.delete(0, strBuf.length());

                    fromIndex = endIndex + 1;

                }


                carRequestMongoService.insertList(list);
                if (rSize > tempString.length()) {

                    strBuf.append(tempString.substring(fromIndex, tempString.length()));

                } else if(rSize == tempString.length()){
                    strBuf.append(tempString.substring(fromIndex, rSize));

                }
                System.out.println("lineNum ="+ lineNum);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取错误!");
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel != null){
                try{
                    inChannel.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            long time = (System.currentTimeMillis()-time1)/1000;
            System.out.println("共花费"+time+"秒");
        }

    }

}
