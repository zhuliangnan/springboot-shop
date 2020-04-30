package com.springboot.springbootshop.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;

public class PhoneAndEmailCheck {
	
	 /*检查字符串是否为电话号码的方法,并回传true or false的判断值*/ 
		public static boolean isPhoneNumberValid(String mobiles) {
		    Pattern p = Pattern.compile("^1(3|5|7|8|4)\\d{9}");
		    Matcher m = p.matcher(mobiles);
		    return m.matches();
		}
	/*    public static boolean isPhoneNumberValid(String mobiles){    
	         Matcher m = null;  
	        if(mobiles.trim().length()>0){  
	        Pattern p = Pattern.compile("^((13[0-9])|(15[0-3])|(15[7-9])|(18[0,5-9]))\\d{8}$");       
	        m= p.matcher(mobiles);       
	        }  
	        else{  
	               
	            return false;  
	        }  
	        return m.matches();       
	    }   	
	*/

		 public static boolean isEmail(String email){     
		     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		        Pattern p = Pattern.compile(str);     
		        Matcher m = p.matcher(email);     
		           
		        return m.matches();     
		    } 
		 
		 
		 
		 // 测试主函数  
		    public static void main(String args[]) {  
		        
		        System.out.println("邮箱验证结果：" + isEmail("2569471714@qq.com"));  
		        
		        System.out.println("手机验证结果：" + isPhoneNumberValid("17766230294"));  
		  
		    }  
		 
		} 

	

