package com.epam.project.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


public class UploadFile {

	private final int maxFileSize = 1024 * 1024 * 3;
	private final int maxMemSize = 1024 * 1024 * 3;
	private final int threshold_size = 1024 * 1024 * 4;
	private String patch="";
	private String uploadPath = null;
	private ServletFileUpload upload;

	public String uploadFile(Part part, ServletContext servletContext, String path)
			throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(threshold_size);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(maxFileSize);
		upload.setSizeMax(maxMemSize);
		
		
		
		String fileName = extractFileName(part);
		try{
			if(getExtension(part).contains("image")&&path==null){
				patch +="photo";
			}
			else{
				if(path==null){
					patch +="workpatch";
				}else{
					patch +="workpatch"+File.separator+path;
				}
				
			}
		}catch(Exception e){
			if(path==null){
				patch +="workpatch";
			}else{
				patch +="workpatch"+File.separator+path;
			}
		}
		
		uploadPath =servletContext.getRealPath("")+"upload"+File.separator+patch;
		
		File directory = new File(uploadPath);
		if(!directory.exists()){
			directory.mkdirs();
		}
		
		
		
		OutputStream out = null;
		InputStream input = null;
		try {

			File f = new File(uploadPath + File.separator + fileName);

			out = new FileOutputStream(f);
			input = part.getInputStream();

		
			int read = 0;
			byte[] bytes = new byte[1024];

			
			while ((read = input.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			return patch+File.separator+fileName;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				input.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}

	public String uploadFileFromNET(String Url, ServletContext servletContext, String path)
			throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(threshold_size);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(maxFileSize);
		upload.setSizeMax(maxMemSize);
		
		
		
		if(path==null){
			patch ="workpatch";
		}else{
			patch ="workpatch"+File.separator+path;
		}
		uploadPath =servletContext.getRealPath("")+"upload"+File.separator+patch;
		File directory = new File(uploadPath);
		if(!directory.exists()){
			directory.mkdir();
		}
		URL url = new URL(Url);
		String fileName = Url.replaceAll("[/?:a-z.=]", "")+".jpg";
		try {
			String fileUrl = fileName;

			File f = new File(uploadPath + File.separator + fileUrl);

			while (f.exists() && !f.isDirectory()) {
				Random random = new Random();
				fileUrl =random.nextInt(Integer.MAX_VALUE)+ fileName;
				f = new File(uploadPath + File.separator + fileUrl);
			}

			FileUtils.copyURLToFile(url, f);
			
			return patch + File.separator + fileUrl;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String uploadBundle(Part part, ServletContext servletContext, String name)
			throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(threshold_size);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(maxFileSize);
		upload.setSizeMax(maxMemSize);

		uploadPath =servletContext.getRealPath("")+"WEB-INF\\classes\\";
		
		File directory = new File(uploadPath);
		if(!directory.exists()){
			directory.mkdirs();
		}
		
		
		
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
			}
		
			return uploadPath+File.separator+name;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				input.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}

	
	public  String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return new Random().nextInt(Integer.MAX_VALUE)+"_"
						+ s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	public  String getExtension(Part part) throws IOException{
		return Files.probeContentType(new File(extractFileName(part)).toPath());
	}
}
