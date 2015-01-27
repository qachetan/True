package com.True.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadCSVFile {
	
	private String filePath;
	public ReadCSVFile(String filePath){
		this.filePath = filePath;
	}
	
	public ArrayList<String> getSKUFromCSV(){	
		ArrayList<String> sku = new ArrayList<String>(); 
		try { 				
	      String csvFile = filePath;
	      //create BufferedReader to read csv file
	      BufferedReader br = new BufferedReader(new FileReader(csvFile));
	      String line = "";
	      StringTokenizer st = null;
	      int lineNumber = 0;      
	      
	      //read comma separated file line by line
	      while ((line = br.readLine()) != null) { 
	        //use comma as token separator
	        st = new StringTokenizer(line, ",");
	        if(lineNumber!=0 ){
	        	st.hasMoreTokens();		      
	        	sku.add(st.nextToken());
	        }	       
	        lineNumber++;	       
	      }

	    } catch (Exception e) {
	      System.err.println("CSV file cannot be read : " + e);
	    }
	    return sku;
	}

}