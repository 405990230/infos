package com.bmw.boss.infos.exportExcle.bmw;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by qxr4383 on 2018/3/12.
 */
public class ImportByNIO {
    public static Integer[] readTxtByNIO(String filePath) {
        //计数
        //id5WeatherNum，id5NewsNum，id5DianPingNum，dzhstock，
        // id4WeatherMain，id4WeatherWidget，id4WeatherSplitscreen，id4WeatherTotal
        // id4NewsNum，id4DianPingNum，greatWisdom，id4Aqi，gas（高德加油站），park（高德停车场），flightStatus（航班管家）
        Integer[] appsNum = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        FileInputStream fis = null;
        FileChannel inChannel = null;
        int bufSize = 1024*10;
        try {
            fis = new FileInputStream(filePath);
            inChannel = fis.getChannel();
            System.out.println("file size --->"+inChannel.size());
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
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {

                    String line = tempString.substring(fromIndex, endIndex);

                    line = new String(strBuf.toString() + line);

                    //System.out.println(line);
                    if(line.contains("/id5-weather")){//id5天气
                        appsNum[0]++;
                    }else if(line.contains("/id5-news/categories")){//id5新闻
                        appsNum[1]++;
                    }else if(line.contains("/id5-dianping")){//id5点评
                        appsNum[2]++;
                    }else if(line.contains("/dzhstock/id5/v1/?type=hsidx")){//id5大智慧
                        appsNum[3]++;
                    }else if(line.contains("/weatherInfo")){//ℹ️d4   weather的main主页面
                        appsNum[4]++;
                    }else if(line.contains("/widget")){//id4  weather的widget页面
                        appsNum[5]++;
                    }else if(line.contains("/splitscreen")){//id4  weather的splitscreen页面
                        appsNum[6]++;
                    }else if(line.contains("/id4-weather/rest/v1")){
                        //因为id4  WeatherTotal为前三个的总和，所以该条件进不来
                        appsNum[7]++;
                    }else if(line.contains("/id4-news/rest/v1/categories")){//id4新闻
                        appsNum[8]++;
                    }else if(line.contains("/dianping/dianping")){//id4点评
                        appsNum[9]++;
                    }else if(line.contains("/great-wisdom")){//id4大智慧
                        appsNum[10]++;
                    }else if(line.contains("/aqi/aqi")){//id4  AQI
                        appsNum[11]++;
                    }else if(line.contains("flight-status")){//id4  航班管家
                        appsNum[12]++;
                    }else if(line.contains("autonavi-gas")){//id4 高德加油站
                        appsNum[13]++;
                    }else if(line.contains("autonavi-park")){//id4 高德停车场
                        appsNum[14]++;
                    }
                    strBuf.delete(0, strBuf.length());

                    fromIndex = endIndex + 1;
                    lineNum++;
                }

                if (rSize > tempString.length()) {

                    strBuf.append(tempString.substring(fromIndex, tempString.length()));

                } else if(rSize == tempString.length()){
                    strBuf.append(tempString.substring(fromIndex, rSize));

                }
            }
            appsNum[7] = appsNum[4]+appsNum[5]+appsNum[6];
            System.out.println("appsNum = "+ Arrays.toString(appsNum));
        } catch (Exception e) {
            System.out.println("文件读取错误!");
            e.printStackTrace();
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

        }
        return appsNum;
    }


    public static void readTxtByNIOofWeather(String filePath) {
        //计数
        int id5WeatherNum = 0;
        long time1 = System.currentTimeMillis();
        FileInputStream fis = null;
        FileChannel inChannel = null;
        int bufSize = 1024*1000;
        HashMap<String,String> map = new HashMap<>();
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
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {

                    String line = tempString.substring(fromIndex, endIndex);

                    line = new String(strBuf.toString() + line);


                    lineTxtArray = line.split("\\|");
                    //System.out.println(Arrays.toString(lineTxtArray));

                    if(lineTxtArray.length>6){
                        //System.out.println(lineTxtArray[5]);
                        map.put(lineTxtArray[5],lineTxtArray[4]);
                    }
                    strBuf.delete(0, strBuf.length());

                    fromIndex = endIndex + 1;
                    lineNum++;
                }

                if (rSize > tempString.length()) {

                    strBuf.append(tempString.substring(fromIndex, tempString.length()));

                } else if(rSize == tempString.length()){
                    strBuf.append(tempString.substring(fromIndex, rSize));

                }
                System.out.println("lineNum ="+ lineNum);

            }
        } catch (Exception e) {
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
            System.out.println(map.size());

            long time2 = System.currentTimeMillis();
            long time = (time2-time1)/1000;
            System.out.println("共花费"+time+"秒");
        }

    }

    public static void main(String[] args) {
        String filePath2 = "/Users/qxr4383/Documents/work/logger/prod/id5/id5-weather/2018/id5/weather-statistic.csv.2018-01";
        String filePath = "/Users/qxr4383/Documents/work/logger/prod/id5/id5-weather/2018/weather.txt";
        String filePath1 = "/Users/qxr4383/Documents/work/logger/prod/access/2018/01/1.txt";
        System.out.println(filePath);
        readTxtByNIO(filePath1);
        //readTxtByNIOofWeather(filePath);
    }
}
