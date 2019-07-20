package com;

import java.io.File;
import java.util.Date;

public class RenameDemo {
    public static void main(String[] args) {
    	reanameVideoFile();
         
    }
    
    public static void reanameVideoFile() {
    	String source = "E:\\BaiduYunDownload\\亿级流量电商详情页系统实战（第二版）：缓存架构+高可用服务架构+微服务架构";
    	File files = new File(source);
    	File[] list = files.listFiles();
    	int n = 1;
    	for (File file : list) {
			//System.out.println(file.getName() + " " + file.isDirectory());
			if(file.isDirectory()) {
				String fileName = file.getName();
				String aviFolder = file.getAbsolutePath() +"/"+"视频";
				File[] listFiles = new File(aviFolder).listFiles();
				if(listFiles.length == 0) continue;
				File avi = listFiles[0];
				System.out.println(n++ + avi.getAbsolutePath());
				String t = source + "video" + "/"+ fileName +".avi";
				avi.renameTo(new File(t));
				//if(n ==2) break;
			}
		}
    }
 
    public void test1() {
    	 //文件存在的路;
        String path = "G:\\temp3\\t1";
         
        File rootDir = new File(path);
        String [] filelist = rootDir.list();
         
        for(int i=0;i<filelist.length;i++){
            //得到原图片的绝对路径：如    D:\pic\阿三+913436199606071044.jpg
            String name = rootDir.getAbsolutePath()+"\\"+filelist[i];
             
            //根据图片的绝对路径创建一个文件对象，用来重命名
            File file = new File(name);        
             
            //拼接重命名之后的绝对路径+新文件名（D:\pic\913436199606071044）
            String newName =rootDir.getAbsolutePath()+"\\"+ i+"_"+new Date().getTime();
            System.out.println("newname:"+newName+".jpg");
             
            System.out.println(file.renameTo(new   File(newName+".jpg")));
             
             
        }
         
    }
}
