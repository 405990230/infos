package com.bmw.boss.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by qxr4383 on 2018/3/12.
 */
public class MyUtils {
    //生成32位UUID
    public static final String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //生成随机数
    public static final String getRandom()
    {
        return String.valueOf(RandomUtils.nextInt(999));
    }

    //判断为null或空字符串
    public static final boolean isEmpty(String s)
    {
        return StringUtils.isEmpty(s);
    }

    //获取格式化的时间,参数都为空则返回当前时间
    public static final String getFormatDate(String style, Date date)
    {
        return new SimpleDateFormat(isEmpty(style) ? "yyyy-MM-dd" : style).format(date == null ? new Date() : date);
    }

    //URL 编码, Encode默认为UTF-8.
    public static final String urlEncode(String s) throws UnsupportedEncodingException
    {
        return URLEncoder.encode(s, "UTF-8");
    }

    //URL 解码, Encode默认为UTF-8.
    public static final String urlDecode(String s) throws UnsupportedEncodingException
    {
        return URLDecoder.decode(s, "UTF-8");
    }

    // 获得用户远程地址
    public static final String getRemoteAddr(HttpServletRequest request)
    {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr))
        {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }
        else if (StringUtils.isNotBlank(remoteAddr))
        {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }
        else if (StringUtils.isNotBlank(remoteAddr))
        {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }
}
