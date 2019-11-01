package com.demo;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.SupportedValuesAttribute;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.PrinterState;

import javafx.print.Printer;
import javafx.print.PrinterJob;

public class PrintTest {

	public static void main(String[] args) {
		int rowNum = (int) Math.floor(1.5);
		System.out.println(rowNum);
		PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
		PrintServiceAttributeSet attributes2 = defaultService.getAttributes();
		PrinterState attributes3 = defaultService.getAttribute(PrinterState.class);
		Object attributes5 =  defaultService.getSupportedAttributeValues(PrinterState.class, null, null);
		MediaPrintableArea defaultAttributeValue = (MediaPrintableArea) defaultService.getDefaultAttributeValue(MediaPrintableArea.class);
		Object defaultAttributeValue1 = defaultService.getDefaultAttributeValue(SupportedValuesAttribute.class);
		Object defaultAttributeValue2 = defaultService.getDefaultAttributeValue(PrintRequestAttribute.class);
		System.out.println(defaultAttributeValue.getHeight(MediaPrintableArea.MM) + ","+defaultAttributeValue.getWidth(MediaPrintableArea.MM));
		System.out.println(defaultAttributeValue1);
		System.out.println(defaultAttributeValue2);
		System.out.println("========");
		PrintServiceAttributeSet attributes = defaultService.getAttributes();
		System.out.println(attributes);
		System.out.println("========");

		Media[] objs = (Media[]) defaultService.getSupportedAttributeValues(Media.class, null, null);
		for (Media obj : objs) {
			System.out.println("---:"+obj.getClass());
			System.out.println("---" + obj + "," + obj.getName() + "," + obj.getValue() + ",");
			
			 if (obj instanceof MediaSizeName) { 
				 System.out.println("纸张型号：" + obj); 
				 if(obj.toString().equals("PR (4x6)") || "(6x4)".equals(obj.toString())) {
					 
				 }
			 } else if (obj instanceof MediaTray) { System.out.println("纸张来源：" + obj); }
			 
		}
		System.out.println("========");

		MediaPrintableArea[] objs2 = (MediaPrintableArea[]) defaultService.getSupportedAttributeValues(MediaPrintableArea.class, null, null);
		for (MediaPrintableArea obj : objs2) {
			System.out.println("---:"+obj.getClass());
			System.out.println("---" + obj + "," + obj.getName() + "," + obj.getHeight(MediaPrintableArea.MM) + ","+obj.getWidth(MediaPrintableArea.MM));
		}
	}

}
