package com.bmw.boss.infos.app.util;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by qxr4383 on 2018/12/19.
 */
public class TollUtil {

    static BASE64Encoder encoder = new BASE64Encoder();
    static BASE64Decoder decoder = new BASE64Decoder();

    /**
     * base64开头部分
     * @param suffix
     * @return
     */
    public static String imgsBase (String suffix){
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("data:image/");
        sBuilder.append(suffix);
        sBuilder.append(";base64,");
        return sBuilder.toString();
    }

    /**
     * 获取文件扩展名称
     * @param fileName
     * @return
     */
    public static String suffixName (String fileName){
        if (StringUtils.isNotBlank(fileName)) {
            return fileName.trim().substring(fileName.trim().lastIndexOf(".")+1);
        }
        return null;
    }

    /**
     * 将二进制转换为图片
     * @param base64String base64转换后的
     * @param outPath 输出的地址
     */
    public static void base64StringToImage(String base64String,String outPath) {
        try {
            byte[] bytes = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage bi1 = ImageIO.read(bais);
            File file = new File(outPath);// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", file);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取图片流
     * @param inStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 将本地图片转换base64 的二进制 e:/123.jpg
     * @param imgPath
     * @return
     */
    public static String getImageBinary(String imgPath) {
        File f = new File(imgPath.trim());
        BufferedImage bi;
        String prefix = TollUtil.suffixName(imgPath);
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, prefix , baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
            byte[] bytes = baos.toByteArray();

            return TollUtil.imgsBase(prefix)+encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
