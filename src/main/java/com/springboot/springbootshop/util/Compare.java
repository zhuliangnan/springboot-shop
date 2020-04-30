package com.springboot.springbootshop.util;

public class Compare {
	
	
	public double[] sort(double x,double y,double z){ 
		
		double[] d=new double[50];
		double temp;  
        if(x > y){  
            temp = y;  
            y = x;  
            x = temp;  
        }//从小到大：x y(x 与 y的顺序排列好了）  
          
        if(x > z){  
        	
            System.out.println(z + " "+ x + " " + y);  
           d[0]=z;d[1]=x;d[2]=y;
        }else{  
            if(y < z){  
                System.out.println(x + " "+ y + " " + z);
                d[0]=x;d[1]=y;d[2]=z;
            }else{  
                System.out.println(x + " "+ z + " " + y);  
                d[0]=x;d[1]=z;d[2]=y;
            }  
        }
		return d;
		 
          
          
          
    }  
      
      
    public static void main(String[] args) {  
    	double[] a=new Compare().sort(3000,5000, 1000);
    	System.out.println(""+a[0]+" "+a[1]+"我  "+a[2]);
       
  
    }  
  

}
