package com;

import java.io.File;
import java.util.Date;

public class RenameDemo {
    public static void main(String[] args) {
        //文件存在的路径
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
