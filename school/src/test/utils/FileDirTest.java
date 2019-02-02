package test.utils;


import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  
/** 
 *  @FileName:FileDirTest.java 
 *  @Time：2016-9-11 
 *  递归遍历文件，可指定文件名样式或文件格式 
 */  
public class FileDirTest {  
  
        //递归打印文件名  
        public static void goIn(File f){  
            //先判断文件是否存在  
            if(!f.exists()){  
                return ;  
            }  
              
            //判断是否是文件  
            //是否是gif图片(如果需要其他规则也可以在此定义)  
            if(f.isFile()&&f.getName().endsWith(".jar")){  
                System.out.println("file:"+f.getPath());  
                  
                //做文件复制  
                try {  
                    //获取图片输入流  
                    FileInputStream fis = new FileInputStream(f.getPath());  
                    //获取或创建目录文件夹  
                    File newDir = new File("D:/var/jars");  
                    if(!newDir.exists()){  
                        newDir.mkdir();  
                    }  
                    //向新建文件夹中创建输出流文件  
                    FileOutputStream fos = new FileOutputStream(newDir.getPath()+File.separator+f.getName());  
                    //定义一个永固暂存的字节流数组  
                    byte[] buff = new byte[1024];  
                    //每次读取数据的长度  
                    int len=0;  
                    //循环读取到buff中，如果len长度小于0，文件到底  
                    while((len = fis.read(buff))>0){  
                        fos.write(buff,0,len);  
                    }  
                    //输入、输出流的关闭  
                    fis.close();  
                    fos.close();  
                      
                } catch (Exception e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
                  
            //如果是文件夹，就继续递归  
            }else if(f.isDirectory()){  
                File[] files = f.listFiles();  
                for(File file:files){  
                    goIn(file);  
                }  
            }  
        }  
          
      
        public static void main(String[] args) throws IOException {  
              
//          System.out.println(File.separator); // \  
            File dir = new File("D:/source_jar/server_src/jboss/jboss-eap-6.2/modules/system/layers");  
            if(dir.exists()){  
                goIn(dir);  
            }  
        }  
} 