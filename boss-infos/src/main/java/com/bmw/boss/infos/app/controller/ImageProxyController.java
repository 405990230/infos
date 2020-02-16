package com.bmw.boss.infos.app.controller;

import com.bmw.boss.infos.app.config.Imageinitialization;
import com.bmw.boss.infos.app.service.ImageURLService;
import com.bmw.boss.infos.app.util.I18nCategoriesForNews;
import com.bmw.boss.infos.app.util.ImageUtil;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

/**
 * Created by qxr4383 on 2018/5/10.
 */
@RestController
@RequestMapping(value="/image")
public class ImageProxyController {

    private static Logger logger = LoggerFactory.getLogger(ImageProxyController.class);

    @Autowired
    private Imageinitialization imageinitialization;

    @Autowired
    private ImageURLService imageURLService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet responseEvo
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @RequestMapping(value = {"/info","/bonappnews/info"})
    public void getImageForNews( HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(value="url", required=false) String encodedImageUrl,
                         @RequestParam(value="maxWidth", required=false) String maxWidth,
                         @RequestParam(value="maxHeight", required=false) String maxHeight) throws Exception{
        try{
            if(encodedImageUrl != null) {
                String imageUrl = URLDecoder.decode(encodedImageUrl, "UTF-8");
                if(request.getRequestURI().contains("/bonappnews")){
                    imageUrl = imageUrl.substring(0,imageUrl.lastIndexOf("/"));
                }
                HttpResponse imageDataResponse = imageURLService.getImage2(imageUrl);
                if(imageDataResponse.getStatusLine().getStatusCode() == 200){
                    response.setContentType(imageDataResponse.getFirstHeader("Content-Type").getValue());
                    response.setHeader("Access-Control-Allow-Origin", "*");
                    if(maxWidth != null && maxHeight != null){
                        ImageUtil.compressImage(imageDataResponse.getEntity().getContent(), response.getOutputStream(), Integer.parseInt(maxWidth), Integer.parseInt(maxHeight));
                    }else{
                        imageDataResponse.getEntity().writeTo(response.getOutputStream());
                    }
                } else{
                    logger.warn( "Fetching image:" + imageUrl + " failed with status: " + imageDataResponse.getStatusLine().getStatusCode());
                    response.setStatus(imageDataResponse.getStatusLine().getStatusCode());
                }
            }
        } catch (Exception e){
            response.setStatus(500);
            logger.error("3rd party server error",e );
        } finally {
            if(response.getStatus() != 200){
                response.setContentType("image/png");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.getOutputStream().write(imageinitialization.getPng404());
            }
        }
        response.flushBuffer();
    }


    @RequestMapping(value = {"/info2"})
    public String execute(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          @RequestParam(value="url", required=false) String encodedImageUrl) {
        try {
            String imageUrl = URLDecoder.decode(encodedImageUrl, "UTF-8");
            // img为图片的二进制流
            byte[] img = imageURLService.getImage(imageUrl);
            httpServletResponse.setContentType("image/png");
            OutputStream os = httpServletResponse.getOutputStream();
            os.write(img);
            os.flush();
            os.close();
        } catch (Exception e){
            logger.error("3rd party server error",e );
        }
        return "success";
    }

    @RequestMapping(value = {"/info3"})
    public String execute2(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          @RequestParam(value="url", required=false) String encodedImageUrl) {
        try {
            String imageUrl = URLDecoder.decode(encodedImageUrl, "UTF-8");
            String base64String = imageURLService.getURLImage(imageUrl);
            BASE64Decoder decoder = new BASE64Decoder();
            //将二进制转换为图片
            byte[] img = decoder.decodeBuffer(base64String);
            httpServletResponse.setContentType("image/png");
            OutputStream os = httpServletResponse.getOutputStream();
            os.write(img);
            os.flush();
            os.close();
        }  catch (Exception e){
            logger.error("3rd party server error",e );
        }
        return "success";
    }

    @RequestMapping(value = {"/info4"})
    public String downloadPicture(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          @RequestParam(value="url", required=false) String encodedImageUrl) {
        try {
            String imageUrl = URLDecoder.decode(encodedImageUrl, "UTF-8");
            //String savePath= "/Users/qxr4383/Documents/work/logger/image";
            String savePath=I18nCategoriesForNews.class.getClassLoader().getResource("image").getPath();
            // img为图片的二进制流
            imageURLService.downloadPicture(imageUrl,imageUrl.substring(imageUrl.lastIndexOf("/")+1),savePath);
        } catch (Exception e){
            logger.error("3rd party server error",e );
        }
        return "success";
    }
}
