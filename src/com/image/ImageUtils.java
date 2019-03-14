package com.image;
 
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import sun.misc.BASE64Encoder;
 
public class ImageUtils {
    public static void cutJPG(InputStream input, OutputStream out, int x,
            int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();
            
            System.out.println(reader.getWidth(0));
            System.out.println(reader.getHeight(0));
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, "jpg", out);
        } finally {
            imageStream.close();
        }
    }
    
    
    public static void cutPNG(InputStream input, OutputStream out, int x,
            int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();
            
            System.out.println(reader.getWidth(0));
            System.out.println(reader.getHeight(0));
            
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, "png", out);
        } finally {
            imageStream.close();
        }
    }
    
    public static void cutImage(InputStream input, OutputStream out, String type,int x,
            int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            String imageType=(null==type||"".equals(type))?"jpg":type;
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(imageType);
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, imageType, out);
        } finally {
            imageStream.close();
        }
    }
 
    public static BufferedImage getBufferdImage(String imageUrl) throws IOException {
    	URL url = new URL(imageUrl);
    	BufferedImage image = ImageIO.read(url);
    	return image;
    }
    public static BufferedImage  cutImage(BufferedImage source,int x, int y, int w, int h) {
    	return source.getSubimage(x, y, w, h);
    }
    public static String toBase64(BufferedImage source) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(source, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Img = encoder.encode(outputStream.toByteArray());
        return base64Img;
    }
    public static String cutImageAndToBase64(BufferedImage source,int x, int y, int w, int h) throws IOException {
    	BufferedImage bi = cutImage(source, x, y, w, h);
    	return toBase64(bi);
    }
    
    public static void main(String[] args) throws Exception {
       
    }
}
