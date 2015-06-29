package com.epam.project.controler.uploadLanguage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.epam.project.command.Action;
import com.epam.project.db.model.Language;
import com.epam.project.db.service.LanguageService;
import com.epam.project.util.file.UploadFile;

public class UpdatePatternLanguage implements Action {

	private String message;
	private String status;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		Map<String, String> testBandle;
		Map<String, String> newBandle;
		String pachBundle = request.getRealPath("") + "WEB-INF\\classes\\";
		Part file = request.getPart("bandle");
		status = "success";
		
	
		if(file.getSize() >0){
		UploadFile upload = new UploadFile();
		String fileName = upload.uploadBundle(file, request.getServletContext(),
				"i18ntest.properties");
		
		File fileTestBanble = new File(pachBundle + "i18n.properties");
		File fileNewBandle = new File(fileName);
		
		testBandle = getTestMap(fileTestBanble);
		newBandle = getMap(fileNewBandle, res);
		
		
		if(newBandle.size()>=testBandle.size()&&equalsMap(newBandle, testBandle, res)){
			message = res.getString("uploadLanguage.updatePattern.success");
			
			File deleteFile= new File(pachBundle+"i18n.properties");
			
		deleteFile.delete();
			
			
			String newFilePath = fileNewBandle.getAbsolutePath().replace(fileNewBandle.getName(), "") + "i18n.properties";
			  File newFile = new File(newFilePath);

			  try {
			    Files.move(fileNewBandle.toPath(), newFile.toPath());
			  } catch (IOException e) {
			    e.printStackTrace();
			  }
			
			
			
			
		}
		}else{
			status = "fail";
			message = res.getString("uploadLanguage.updatePattern.error");
		}
		try {
			
			response.getWriter().print(new JSONObject().put(status, message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "updateP";
	}
	
	private Map<String, String> getMap(File file, ResourceBundle res) {
		Map<String, String> map = new HashMap<>();
		int count = 0;
		String line = null;
		BufferedReader input = null;
		try {
			 input = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));

			while ((line = input.readLine()) != null) {
				count++;
				
				line= line.replaceAll("#.*", "");
				line= line.replaceAll("п»ї", "");
				if(!line.isEmpty()){
				String[] mass = line.split("=");

				try {
					if(!mass[1].isEmpty()){
						throw new IOException();
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO: handle exception
				}
				
				map.put(mass[0], null);
				if (mass[0].isEmpty()) {
					throw new IOException();
				}
				}
			}
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			status = "fail";
			message = res.getString("uploadLanguage.updatePattern.error.line") + count + " "+res.getString("uploadLanguage.updatePattern.error.line.incorect")+" " + line;
		}finally{
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return map;
	}

	private Map<String, String> getTestMap(File file) {
		Map<String, String> map = new HashMap<>();
		String line = null;
		BufferedReader input = null;
		try {
			 input = new BufferedReader(new InputStreamReader(
					new FileInputStream(file),"UTF-8"));

			while ((line = input.readLine()) != null) {
				line= line.replaceAll("#.*", "");
				line= line.replaceAll("п»ї", "");
				if(!line.isEmpty()){
				map.put(line.replaceAll("=", "").toString(), null);
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}finally{
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return map;
	}

	private Boolean equalsMap(Map<String, String> newBandel,
			Map<String, String> testBandel, ResourceBundle res) {
		int sizeNew = newBandel.size();
		int sizeTest = testBandel.size();
		String line = null;
		try {
			if (sizeNew < sizeTest) {
				line = res.getString("uploadLanguage.updatePattern.error.sizeStandart") +" "+ sizeTest
						+ res.getString("uploadLanguage.updatePattern.error.sizeNew")+" " + sizeNew;
				throw new Exception();
			}
			for (java.util.Map.Entry<String, String> entry : testBandel
					.entrySet()) {

				if (!newBandel.containsKey(entry.getKey())) {
					line =res.getString("uploadLanguage.updatePattern.error.contains")+" "+entry.getKey();
					throw new Exception();
				}

			}
			return true;
		} catch (Exception e) {
			status = "fail";
			message = line;
			return false;
		}
}
	
}
