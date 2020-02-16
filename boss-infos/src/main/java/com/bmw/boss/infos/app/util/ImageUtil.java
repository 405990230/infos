package com.bmw.boss.infos.app.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Created by qxr4383 on 2018/5/10.
 */
public class ImageUtil {

    public static void compressImage(InputStream imageInput, OutputStream os, int width, int height){
        try {
            BufferedImage originalImage;
            ImageInputStream stream = ImageIO.createImageInputStream(imageInput);
            Iterator iter = ImageIO.getImageReaders(stream);
            if (!iter.hasNext()) {
                return;
            }
            ImageReader reader = (ImageReader) iter.next();
            ImageReadParam param = reader.getDefaultReadParam();
            reader.setInput(stream, true, true);
            originalImage = reader.read(0, param);

            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImage = resizeImage(originalImage, type, width, height);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            ImageIO.write(resizeImage, reader.getFormatName(), bos);
            bos.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height){

        Dimension newDimension = getScaledDimension(new Dimension(originalImage.getWidth(), originalImage.getHeight()),
                new Dimension(width, height));
        width = newDimension.width;
        height = newDimension.height;
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }
}
