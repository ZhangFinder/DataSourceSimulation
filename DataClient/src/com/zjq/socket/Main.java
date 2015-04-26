package com.zjq.socket;

import java.text.DecimalFormat;
import java.util.Random;

import com.zjq.tools.RandomData;

public class Main {
   public static void main(String args[]){
    String str="1-1-219.36-0.75-24.3-0.41";
    String data[] =str.split("-");
    for(int i=0;i<data.length;i++){
    	System.out.println(i+" : "+ data[i]);
    }
    	
    }
 }

   

