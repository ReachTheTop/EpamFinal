package com.epam.project.util.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class FileDownload {

	 private static final int BUFSIZE = 32096;
	 private  String filePath;
	public void downloadFile(String pathFile, ServletContext servletContext, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		if(pathFile.contains("WEB-INF")){
		filePath = servletContext.getRealPath("") + File.separator
                + pathFile;
		}else{
			filePath = servletContext.getRealPath("") + File.separator+"upload"+File.separator
	                + pathFile;
		}
        File file = new File(filePath);
        int length = 0; 
        ServletOutputStream outStream = response.getOutputStream();
        response.setContentType("text/html;charset=UTF-8");
        response.setContentLength((int) file.length());
        String fileName = (new File(filePath)).getName().replaceAll("[0-9]+_", "");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + fileName + "\"");
 
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
 
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            outStream.write(byteBuffer, 0, length);
        }
 
        in.close();
        outStream.close();
	}
}
/*
Writer out = null;
BufferedReader  input = null;
try {

	File f = new File(uploadPath + File.separator + name);

	out =new OutputStreamWriter(
            new FileOutputStream(f), "UTF-8");
         
	input = new BufferedReader(new InputStreamReader(
             part.getInputStream()));
	

	int read = 0;
	//byte[] bytes = new byte[1024];

	
	while ((read = input.read())!= -1) {
		
		out.write(read);
	}*/
