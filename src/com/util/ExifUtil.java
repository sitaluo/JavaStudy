package com.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;



public class ExifUtil {
	 public static void main(String[] args) throws Exception {
       // System.out.println(transformMm2px(72,210));
        //printExifs();
		 String file = "G:\\printtest\\_MG_3555_1555759440000.JPG";
        getDate(file);
        System.out.println("=================ss=====");
	 }
	 
	 public static void printExifs() throws Exception {
         File jpegFile = new File("G:\\printtest\\_MG_3555_1555759440000.JPG");
        
         Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
         for (Directory directory : metadata.getDirectories()) {
        	 	System.out.println("directory.getName:"+directory.getName());
        	    for (Tag tag : directory.getTags()) {
        	        System.out.println(tag.getTagName() + "="+tag.getTagType()+"="+tag.getDescription());
        	        System.out.println(tag);
        	    }
        	    System.out.println("======================");
        	}
	 }
	
	
	 public static Date getDate(String jpgFile) {
		 try {
			 File jpegFile = new File(jpgFile);
				Metadata metadata = null;
				try {
					metadata = JpegMetadataReader.readMetadata(jpegFile);
				} catch (JpegProcessingException | IOException e) {
					//log.error("获取dpi失败：",e);
				}
				ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
				Date a  = exifIFD0Directory.getDate(ExifIFD0Directory.TAG_DATETIME_ORIGINAL);
				System.out.println(a.toLocaleString());
				//log.debug("获取到dpi:"+a + ","+jpgFile);
				//log.info("获取dpi失败,使用默认值300，"+jpgFile);
				return a;
		} catch (Exception e) {
			//log.error("获取dpi失败：",e);
		}
		return null;
	 }
}
