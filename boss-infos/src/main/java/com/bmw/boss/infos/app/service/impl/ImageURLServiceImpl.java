package com.bmw.boss.infos.app.service.impl;

import com.bmw.boss.infos.app.service.ImageURLService;
import com.bmw.boss.infos.app.util.TollUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("imageURLService")
public class ImageURLServiceImpl implements ImageURLService{

    static BASE64Encoder encoder = new BASE64Encoder();

    @Override
    public byte[] getImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10 * 1000);
        InputStream inStream = conn.getInputStream();
        byte[] data = TollUtil.readInputStream(inStream);
        return data;
    }

    @Override
    public HttpResponse getImage2(String imageUrl) throws IOException {
        HttpResponse imageDataResponse = Request.Get(imageUrl)
                .connectTimeout(30000)
                .socketTimeout(30000)
                .execute()
                .returnResponse();

        return imageDataResponse;
    }

    public void downloadPicture(String imageUrl,String filename,String savePath) throws IOException{
        try {
            File sf=new File(savePath);
            if(!sf.exists()){
                sf.mkdirs();
            }
            byte[] img = this.getImage(imageUrl);
            OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename);
            os.write(img);
            // 完毕，关闭所有链接
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Cacheable(value="cacheImage", key="#imageUrl")
    public String getURLImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = TollUtil.readInputStream(inStream);
        //String prefix = TollUtil.suffixName(imageUrl);
        //return TollUtil.imgsBase(prefix)+encoder.encode(data).trim();
        return encoder.encode(data).trim();
    }

}
