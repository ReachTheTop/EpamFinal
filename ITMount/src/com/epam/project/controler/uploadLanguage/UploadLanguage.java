package com.epam.project.controler.uploadLanguage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class UploadLanguage implements Action {

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
		String name = request.getParameter("name");
		String language = request.getParameter("language").toLowerCase();
		String country = request.getParameter("country").toUpperCase();
		Part file = request.getPart("bandle");
		if(!name.isEmpty()&&!language.isEmpty()&&!country.isEmpty()){
		status = "success";
		
		Language lang = LanguageService.getLanguage(language, country);
		if(lang==null&&file.getSize()>0){
		UploadFile upload = new UploadFile();
		String fileName = upload.uploadBundle(file, request.getServletContext(),
				"i18n_"+language+"_"+country.toUpperCase()+".properties");
		
		File fileTestBanble = new File(pachBundle + "i18n.properties");
		File fileNewBandle = new File(fileName);
		
		testBandle = getTestMap(fileTestBanble);
		newBandle = getMap(fileNewBandle, res);
		System.out.println(testBandle);
		System.out.println(newBandle);
		if(testBandle.size()<=newBandle.size()&&equalsMap(newBandle, testBandle, res)){
			message = res.getString("uploadLanguage.uploadPattern.success");
			Language newLanguage = new Language();
			newLanguage.setName(name);
			newLanguage.setCountry(country);
			newLanguage.setLanguage(language);
			newLanguage.setFile("i18n");
			newLanguage.setImage(country.toLowerCase()+".png");
			newLanguage.setActive(true);
			LanguageService.addLanguage(newLanguage);
			
		}
		}else{
			status = "fail";
			message = res.getString("uploadLanguage.uploadPattern.error.exist");
		}
		}else{
			status = "fail";
			message = res.getString("uploadLanguage.uploadPattern.error.notEmpty");
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
		return "upload";
	}

	private Map<String, String> getMap(File file, ResourceBundle res) {
		Map<String, String> map = new HashMap<>();
		int count = 0;
		String line = null;
		BufferedReader input = null;
		try {
			 input = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));

			while ((line = input.readLine()) != null) {
				count++;
				
				line= line.replaceAll("#.*", "");
				line= line.replaceAll("п»ї", "");
				if(!line.isEmpty()){
				String[] mass = line.split("=");

				map.put(mass[0].trim(), mass[1]);
				if (mass[0].isEmpty() || mass[1].isEmpty()) {
					throw new IOException();
				}}
			}
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			status = "fail";
			message = res.getString("uploadLanguage.uploadPattern.error.line")+" "+ count +" "+res.getString("uploadLanguage.uploadPattern.error.line.incorect") +" " + line;

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

	private Map<String, String> getTestMap(File file) {
		Map<String, String> map = new HashMap<>();
		String line = null;
		BufferedReader input = null;
		try {
			 input = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));

			while ((line = input.readLine()) != null) {
				line= line.replaceAll("#.*", "");
				line= line.replaceAll("п»ї", "");
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
			Map<String, String> testBandel, ResourceBundle res) {
		int sizeNew = newBandel.size();
		int sizeTest = testBandel.size();
		String line = null;
		try {
			if (sizeNew < sizeTest) {
				line =res.getString("uploadLanguage.uploadPattern.error.sizeStandart")+ " " + sizeTest
						+" "+ res.getString("uploadLanguage.uploadPattern.error.sizeNew")+" " + sizeNew;
				throw new Exception();
			}
			for (java.util.Map.Entry<String, String> entry : testBandel
					.entrySet()) {

				if (!newBandel.containsKey(entry.getKey())) {
					line = res.getString("uploadLanguage.uploadPattern.error.feild")+" "+entry.getKey()+" "+ res.getString("uploadLanguage.uploadPattern.error.notFound");
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
