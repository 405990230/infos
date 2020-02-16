package com.bmw.boss.infos.app.service;


import org.apache.http.HttpResponse;

import java.io.IOException;

public interface ImageURLService {

    String getURLImage(String imageUrl) throws IOException;

    byte[] getImage(String imageUrl) throws IOException;

    HttpResponse getImage2(String imageUrl) throws IOException;

    void downloadPicture(String urlList,String filename,String savePath) throws IOException;
}
