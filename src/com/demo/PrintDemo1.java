package com.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.PrintQuality;
import javax.swing.JFileChooser;

public class PrintDemo1 {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser(); // 创建打印作业
        int state = fileChooser.showOpenDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // 获取选择的文件
            // 构建打印请求属性集
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
           // pras.add(PrintQuality.HIGH);
           // JobName jobName = new JobName("test", Locale.getDefault());
           // pras.add(jobName);
            // 设置打印格式，因为未确定类型，所以选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
            // 查找所有的可用的打印服务
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            for (PrintService printService2 : printService) {
				System.out.println(printService2.getName());
				System.out.println(printService2.getSupportedDocFlavors());
				DocFlavor[] s = printService2.getSupportedDocFlavors();
				for (DocFlavor docFlavor : s) {
					System.out.println(docFlavor);
				}
			}
            // 定位默认的打印服务
            PrintService defaultService = PrintServiceLookup
                    .lookupDefaultPrintService();
            // 显示打印对话框
            PrintService service = ServiceUI.printDialog(null, 200, 200,
                    printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob(); // 创建打印作业
                    FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}