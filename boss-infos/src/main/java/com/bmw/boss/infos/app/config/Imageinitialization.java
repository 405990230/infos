package com.bmw.boss.infos.app.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class Imageinitialization {

    private byte[] png404;

    @PostConstruct
    public void initLoad() {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream("image/404.png");
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            len = is.read(buf);
            while(len != -1){
                baos.write(buf, 0, len);
                len = is.read(buf);
            }
            this.png404 = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getPng404() {
        return png404;
    }

    public void setPng404(byte[] png404) {
        this.png404 = png404;
    }

}
