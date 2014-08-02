package browser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class ParseProperties {	
    //创建一个Properties容器 
	private Properties prop = new Properties();
	String value = null;
	private static String projectpath = System.getProperty("user.dir");
	
	public  ParseProperties(String propertiespath){		
		this.loadProperties(propertiespath);
	}
	
	private void loadProperties(String propertiespath){
		System.out.println("------------testProperties-------------"); 
        //将properties文件加载到输入字节流中 
		try {
			InputStream in = new FileInputStream(propertiespath);
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			prop.load(br);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //循环输出配置信息 
        /*
        for (Object key : prop.keySet()) { 
           // System.out.println(key + "=" + prop.get(key));
            System.out.print(value);
         */   
        }

    public String getValue(String key){
    	value = prop.getProperty(key).trim();
    	try {
    		value = new String(value.getBytes("gbk"),"UTF-8");
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	return value;
    	}
    public  static void main(String[] args){
    	ParseProperties a = new ParseProperties(projectpath+"\\src\\tool\\test.properties");
		System.out.println(a.getValue("password"));
		System.out.println(projectpath);
    }
}