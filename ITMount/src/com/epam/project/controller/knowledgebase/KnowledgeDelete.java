package com.epam.project.controller.knowledgebase;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.box.view.BoxViewClient;
import com.box.view.BoxViewException;
import com.box.view.Document;
import com.box.view.Session;
import com.epam.project.command.Action;
import com.epam.project.db.model.KnowledgeBase;
import com.epam.project.db.service.HomeWorkService;
import com.epam.project.db.service.KnowledgeBaseService;
import com.epam.project.util.file.DeleteFile;

public class KnowledgeDelete implements Action {
	private String message;
	private String status;
	public static String apiKey = "pnxi84xiuf47ov0o7ohy72jocrqe72ud";

    public static BoxViewClient boxView;

    public static Document document;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String idKbase = request.getParameter("id_kbase");
		
		ResourceBundle res = (ResourceBundle) request.getSession().getAttribute("bundle");
		/*KnowledgeBase kb = KnowledgeBaseService.getKnowledgeBase(Integer.parseInt(idKbase));
		if(kb.getBox_id()!=null){
		
		deleteDocumet(kb.getBox_id());
		}
		DeleteFile.deleteFile(kb.getPath(), request.getServletContext());
		KnowledgeBaseService.delKnowledgeBase(Integer.parseInt(idKbase));*/
		status = "success";
		message = res.getString("knowledgebase.message");
		try {
			
			response.getWriter().print(new JSONObject().put(status, message));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String getName() {

		return "delete";
	}
	
	private void deleteDocumet(final String id){
		
		Thread delete = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					 boxView = new BoxViewClient(apiKey);
					document = boxView.getDocument(id);
					document.delete();
				} catch (BoxViewException e) {
					
				}
				
			}
		});
		delete.start();
	}

}
