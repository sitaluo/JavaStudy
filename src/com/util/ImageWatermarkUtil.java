package com.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import lombok.extern.log4j.Log4j;
@Log4j
public class ImageWatermarkUtil {

	
	public static void markImageByIcon(String imagesFolder, String targetFile) {
		OutputStream os = null;
		try {
			File folder = new File(imagesFolder);
			log.info("合成图片开始：iconPath："+iconPath);
			log.debug("合成图片0");
			int targetPaperWidth = ExifUtil.getPringImagePx(srcImgPath, paperWidth);
			int targetPaperHeight = ExifUtil.getPringImagePx(srcImgPath, paperHeight);
			//int targetPaperHeight = (int) (targetPaperWidth * paperHeight * 1.0 / paperWidth);
			Image srcImg = ImageIO.read(new File(srcImgPath));
			log.debug("合成图片1");
			BufferedImage buffImg = new BufferedImage(targetPaperWidth, targetPaperHeight,
					BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			
			g.drawImage(srcImg.getScaledInstance(targetPaperWidth, targetPaperHeight, Image.SCALE_SMOOTH), 0,
					0, null);
			log.debug("合成图片2");
			// 水印图象的路径水印一般为gif或者png的，这样可设置透明度
			//ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
//			Image img = imgIcon.getImage();
			Image img = ImageIO.read(new File(iconPath));
			log.debug("合成图片3");
			float alpha = 1.0f;// 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			
			if(targetPaperWidth > targetPaperHeight) {
				if(img.getWidth(null) < img.getHeight(null)) {
					img = ImageUtil.Rotate90(img);
				}
			}
			if(targetPaperWidth < targetPaperHeight) {
				if(img.getWidth(null) > img.getHeight(null)) {
					img = ImageUtil.Rotate90(img);
				}
			}
			// 表示水印图片的位置
			Image warter = img.getScaledInstance((int) (watermarkWidthRatio*buffImg.getWidth()), (int) (watermarkHeightRatio*buffImg.getHeight()), Image.SCALE_SMOOTH);
			g.drawImage(warter, (int) (watermarkLeftRatio*buffImg.getWidth()), (int) (watermarkTopRatio*buffImg.getHeight()), null);
			log.debug("合成图片5");
			if(!textColor.equals(Constants.FileNameColor.COLOR_TRANSPARENT)) {
				// 5、设置水印文字颜色
				g.setColor(textColor);
				// 6、设置水印文字Font
				g.setFont(new java.awt.Font("宋体", java.awt.Font.BOLD, buffImg.getHeight() / 20));
				// 7、设置水印文字透明度
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
				// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
				Rectangle2D rect = g.getFontMetrics().getStringBounds(text, g);
				int strWidth = (int) rect.getWidth();
				int strHeight = (int) rect.getHeight();
				g.drawString(text,  strWidth + 100, buffImg.getHeight() - strHeight*2);
			}
			// 9、释放资源
			g.dispose();
			os = new FileOutputStream(targerPath);
			log.debug("合成图片6");
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			log.info("合成图片结束：iconPath："+iconPath);
		} catch (Exception e) {
			log.error("合成图片出错", e);
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				log.error("关闭FileOutputStream出错", e);
			}
		}
	}
	
	public static void main(String[] args) {
		markImageByIcon("G:\\temp2\\111.jpg", "G:\\temp2\\12345.JPG", "G:\\temp2\\test2.jpg" ,Color.gray,"SDF_12347",0.02f,0.04f,0.93f,0.87f,213,103);
		//markImageByIcon("G:\\temp2\\586.png", "G:\\temp2\\56.JPG", "G:\\temp2\\test3.jpg",Color.gray,"SDF_12347", 1.0f,0.2f);
	}

}