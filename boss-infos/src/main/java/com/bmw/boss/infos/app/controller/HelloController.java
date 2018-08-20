package com.bmw.boss.infos.app.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;

/**
 * Created by qxr4383 on 2017/8/21.
 */
//用@Controller声明控制层组件
@Controller
//用@RequestMapping声明bean的访问路径。
@RequestMapping("/api")
public class HelloController implements Serializable{
    private static Logger logger= LoggerFactory.getLogger(HelloController.class);
    /**
     * 方法的要求：
     * 1.公有的
     * 2.返回ModelAndView
     * 3.可以有参数，也可以没有。
     * 	 具体可以写什么参数下次课再说。
     */
    //用@RequestMapping声明方法访问路径
    @RequestMapping("/hello")
    public ModelAndView sayHello() {
        logger.info("Hello, Controller!");
        return new ModelAndView("/index");
    }

    @ResponseBody
    @RequestMapping("/json")
    public String writeJson(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BufferedReader reader = null;
        StringBuffer data = null;
        ClassLoader classLoader = HelloController.class.getClassLoader();
        System.out.println(classLoader.getResource("").getPath());
        try {
            //方法一InputStream
            //InputStream input =  new FileInputStream(classLoader.getResource("newsCategory.json").getFile());
            //reader = new BufferedReader(new InputStreamReader(input));
            //方法二FileReader
            reader = new BufferedReader(new FileReader(classLoader.getResource("").getPath()+ "/json/newsCategory.json"));
            //返回值,使用StringBuffer
            data = new StringBuffer();
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(reader != null)
                reader.close();
        }
//        PrintWriter out = response.getWriter();
//        out.write(data.toString());
//        out.close();
        return data.toString();
    }
}
