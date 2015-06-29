package com.epam.project.controler.uploadLanguage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

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

public class UpdateLanguage implements Action{

	private String message;
	private String status;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		Integer id = Integer.parseInt( request.getParameter("language_id"));
		response.setContentType("application/json");

		Map<String, String> testBandle;
		Map<String, String> newBandle;
		String pachBundle = request.getRealPath("") + "WEB-INF\\classes\\";
		Part file = request.getPart("bandle");
		status = "success";
		
		Language lang = LanguageService.getLanguage(id);
		if(file.getSize() >0){
		UploadFile upload = new UploadFile();
		String fileName = upload.uploadBundle(file, request.getServletContext(),
				"i18ntest_"+lang.getLanguage()+"_"+lang.getCountry()+".properties");
		
		File fileTestBanble = new File(pachBundle + "i18n.properties");
		File fileNewBandle = new File(fileName);
		
		testBandle = getTestMap(fileTestBanble);
		newBandle = getMap(fileNewBandle);
		
	
		if(testBandle.size()<=newBandle.size()&&equalsMap(newBandle, testBandle)&&!name.isEmpty()){
			message = "Update language!";
			
			File deleteFile= new File(pachBundle+"i18n_"+lang.getLanguage()+"_"+lang.getCountry()+".properties");
			
		deleteFile.delete();
			
			
			String newFilePath = fileNewBandle.getAbsolutePath().replace(fileNewBandle.getName(), "") + "i18n_"+lang.getLanguage()+"_"+lang.getCountry()+".properties";
			  File newFile = new File(newFilePath);

			  try {
			    Files.move(fileNewBandle.toPath(), newFile.toPath());
			  } catch (IOException e) {
			    e.printStackTrace();
			  }
			
			lang.setName(name);
			LanguageService.updateLanguage(lang);
			
			
		}
		}else{
			status = "fail";
			message = "Error! such bundel already exists, change country or language!";
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
		return "update";
	}
	
	private Map<String, String> getMap(File file) {
		Map<String, String> map = new HashMap<>();
		int count = 0;
		String line = null;
		BufferedReader input = null;
		
		try {
			 input = new BufferedReader(new InputStreamReader(
					new FileInputStream(file),"UTF-8"));

			while ((line = input.readLine()) != null) {
				count++;
				
				line= line.replaceAll("#.*", "");
				if(!line.isEmpty()){
				String[] mass = line.split("=");
				
				map.put(mass[0].trim(), mass[1]);
				
				if (mass[0].isEmpty() || mass[1].isEmpty()) {
					throw new Exception();
				}
				}
			}
		} catch (Exception e) {
			status = "fail";
			message = "Error! in line " + count + " incorect " + line;
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
				if(!line.isEmpty()){
				map.put(line.replaceAll("=", "").toString().trim(), null);
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
			Map<String, String> testBandel) {
		int sizeNew = newBandel.size();
		int sizeTest = testBandel.size();
		String line = null;
		try {
			 if (sizeNew < sizeTest) {
				line = "Wrong count line! Standart bandle=" + sizeTest
						+ " lines your upload " + sizeNew;
				throw new Exception();
			}
			for (java.util.Map.Entry<String, String> entry : testBandel
					.entrySet()) {

				if (!newBandel.containsKey(entry.getKey())) {
					line = "Error! in "+entry.getKey()+" " + newBandel.get(entry.getKey());
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
