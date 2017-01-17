package com.Metrodigi.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader
{	
	String path =  getFilePath();  	
    public String readApplicationFile(String key)
    { 
    	String value = null;
        try{         	  
	          Properties prop = new Properties();
	          File f = new File(path + "//src//com//Metrodigi//config//application.properties");
	          if(f.exists()){
		          prop.load(new FileInputStream(f));
		          value = prop.getProperty(key); 		                 
          	}
	   }
        catch(Exception e){  
           System.out.println("Failed to read from application.properties file.");  
        }
        return value;
     } 
    
	public String getFilePath()
	{
		String filepath =null;		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		filepath = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return filepath;
	}
	
	public void updateProperty(final String key, final String value) {
        final Properties props = new Properties();
        final String propsFileName = path + "//src//com//Metrodigi//config//name.properties";
        try {
            // first load old property file:
            final FileInputStream configStream = new FileInputStream(
                    propsFileName);
            props.load(configStream);
            configStream.close();

            // modifies new property
            props.setProperty(key, value);

            // save modified property file
            final FileOutputStream output = new FileOutputStream(propsFileName);
            props.store(output, "");
            output.close();

        } catch (final IOException ex) {
            System.out.println(ex);
        }
    }
	
	public String readApplicationFile1(String file, String key)
    { 
    	String value = "";
        try{         	  
	          Properties prop = new Properties();
	          File f = new File(path + "//src//com//Metrodigi//config//"+file+".properties");
	          if(f.exists()){
		          prop.load(new FileInputStream(f));
		          value = prop.getProperty(key); 		                 
          	}
	   }
        catch(Exception e){  
           System.out.println("Failed to read from "+file+".properties file.");  
        }
        return value;
     } 
}