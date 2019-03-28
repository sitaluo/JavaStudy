package com.demo;
import java.awt.PrintJob;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

public class PrintUtil {
    public static void drawImage(String fileName, int count) {
        FileInputStream fin = null;
        try {
            
        	PrintService ps = PrintServiceLookup.lookupDefaultPrintService();

            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(OrientationRequested.PORTRAIT);
            pras.add(new Copies(count));
            pras.add(PrintQuality.HIGH);
            JobName jobName = new JobName(fileName, Locale.getDefault());
            pras.add(jobName);
            
            DocAttributeSet das = new HashDocAttributeSet();
            // 设置打印纸张的大小（以毫米为单位）
            das.add(new MediaPrintableArea(0, 0, 210, 296, MediaPrintableArea.MM));
            fin = new FileInputStream(fileName);

            DocFlavor dof = null;
            if (fileName.endsWith(".gif")) {
                dof = DocFlavor.INPUT_STREAM.GIF;
            } else if (fileName.endsWith(".jpg")) {
                dof = DocFlavor.INPUT_STREAM.JPEG;
            } else if (fileName.endsWith(".png")) {
                dof = DocFlavor.INPUT_STREAM.PNG;
            }
            Doc doc = new SimpleDoc(fin, dof, das);

            DocPrintJob job = ps.createPrintJob();
           
            job.addPrintJobListener(new PrintJobListener() {
				@Override
				public void printJobRequiresAttention(PrintJobEvent pje) {
					System.out.println("printJobRequiresAttention");
				}
				@Override
				public void printJobNoMoreEvents(PrintJobEvent pje) {
					// TODO Auto-generated method stub
					System.out.println("printJobNoMoreEvents");
				}
				@Override
				public void printJobFailed(PrintJobEvent pje) {
					// TODO Auto-generated method stub
					System.out.println("printJobFailed");
					
				}
				@Override
				public void printJobCompleted(PrintJobEvent pje) {
					// TODO Auto-generated method stub
					System.out.println("printJobCompleted");
					
				}
				
				@Override
				public void printJobCanceled(PrintJobEvent pje) {
					System.out.println("printJobCanceled");
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void printDataTransferCompleted(PrintJobEvent pje) {
					System.out.println("printDataTransferCompleted");
					// TODO Auto-generated method stub
					
				}
			});
            job.print(doc, pras);
            fin.close();
            System.out.println("打印成功！文件："+fileName+"数量为："+count);
            System.in.read();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (PrintException pe) {
            pe.printStackTrace();
        }finally {
            //IOUtils.closeQuietly(fin);
        }
    }

    public static void main(String[] args) {
		PrintUtil.drawImage("G:\\temp2\\53.jpg", 1);
		System.out.println("main...");
    }

}