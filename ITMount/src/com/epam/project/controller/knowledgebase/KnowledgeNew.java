package com.epam.project.controller.knowledgebase;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.box.view.BoxViewClient;
import com.box.view.Document;
import com.box.view.Session;
import com.epam.project.command.Action;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.service.KnowledgeBaseService;
import com.epam.project.util.file.UploadFile;


public class KnowledgeNew implements Action{

	 public static String apiKey = "pnxi84xiuf47ov0o7ohy72jocrqe72ud";

	    public static BoxViewClient boxView;

	    public static Document document;

	    public static Session session;
	    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer course_id = Integer.parseInt(request.getParameter("cours_id"));
		Part file = request.getPart("file");
		UploadFile m = new UploadFile();
		 boxView = new BoxViewClient(apiKey);
		
		if (file.getSize()>0) {
			KnowledgeBase kb = new KnowledgeBase();
			kb.setCourse_id(course_id);

			String fileName = m.uploadFile(file, request.getServletContext(),"knowledgeBase_course_id_"+course_id);
			String filePath = request.getServletContext().getRealPath("")+"upload\\"+fileName;
			
			kb.setPath(fileName);
			kb.setAvailable(true);
			kb.setIs_active(true);
			KnowledgeBaseService.addKnowledgeBase(kb);
			boxUpload(filePath, fileName);
			
			
		}
		response.sendRedirect(request.getHeader("Referer"));
		return;
		
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "new";
	}

	private void boxUpload(final String filePath, final String filename){
		Thread upload = new Thread(new Runnable() {
			
			@Override
			public void run() {
				File fileBox = new File(filePath);
				

			        try {
			        	 Map<String, Object> params = new HashMap<String, Object>();
					        ArrayList<String> thumbnails = new ArrayList<String>();
					        thumbnails.add("100x100");
					        thumbnails.add("200x200");
					        params.put("thumbnails", thumbnails);
					        params.put("nonSvg", true);
			            document =boxView.upload(fileBox, params);
			          
			            Calendar expiresAt = Calendar.getInstance();
				    	   expiresAt.add(Calendar.MONTH, 7);

				    	   Map<String, Object> paramsSesion = new LinkedHashMap<String, Object>();
				    	   paramsSesion.put("expiresAt", expiresAt.getTime());
				    	   paramsSesion.put("isDownloadable", true);
				    	   paramsSesion.put("isTextSelectable", false);

				    	   Session session = document.createSession(paramsSesion);
				    	 
			           KnowledgeBase kb =  KnowledgeBaseService.getKnowledgeBase(filename);
			           kb.setBox_session(session.getId());
			           kb.setBox_id(document.getId());
			           KnowledgeBaseService.updateKnowledgeBase(kb);
			       
			        } catch (Exception e) {
			            
			        }

			       
			     
			
			}
		});
		upload.start();
		
	}
}
