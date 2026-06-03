package com.gba.dating4e.core.photo;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Crea imagen en miniatura
 */
@Service
public class AwtBicubicThumbnail {

    private static BufferedImage create(BufferedImage source, int width, int height){
        double thumbRatio = (double) width/height;
        double imageRatio = (double) source.getWidth() / source.getHeight();

        if(thumbRatio < imageRatio)
            height = (int) (width/imageRatio);
        else
            width = (int) (height * imageRatio);

        BufferedImage thumb = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = thumb.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(source, 0, 0, width, height, null);
        g2.dispose();
        return thumb;
    }

    /**
     *
     * @param imageBytes an image is passed as a byte array
     * @return a thumbnail image
     */
    public byte[] thumbnail(byte[] imageBytes){
        try(InputStream is = new ByteArrayInputStream(imageBytes);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            BufferedImage thumbnail = create(ImageIO.read(is), 200, 200);
            ImageIO.write(thumbnail, "jpg", baos);
            return baos.toByteArray();
        }catch (IOException ex){
            throw new UncheckedIOException(ex);
        }
    }
}
