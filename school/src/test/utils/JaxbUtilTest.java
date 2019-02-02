package test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.leo.common.bean.Extension;
import com.utils.JaxbUtil;

public class JaxbUtilTest {

	public static JaxbUtil ju = new JaxbUtil(Extension.class); 
	
	public static void readxml(){
		
		String path = "D:\\devspaces\\ssm_1\\school\\src\\extension-test.xml";
		path = "src\\extension-test.xml";
//		File f = new File(path);
//		Extension temp = (Extension)ju.fromXml(f);
		
		InputStream i = null;
		try {
			i = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//ju.getClass().getResourceAsStream(path);
		System.out.println("i->"+i);
		Extension temp = (Extension)ju.fromXml(i);
		temp.getInstrumentation().getPointcut().size();
		System.out.println("size->"+temp.getInstrumentation().getPointcut().size() 
				+ " name->" + temp.getName() + " method-1->" + 
				temp.getInstrumentation().getPointcut().get(0).getMethod().get(0).getName());
		
		temp.getInstrumentation().getPointcut().get(0).getMethod().get(0).getParameters().getType().get(0).getAttributeName();
	}
	
	
	public static void main(String[] args) {
		
		try {
			readxml();
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		String path = "1/adb";
		System.out.println(path.split("/").length);
	}
}
