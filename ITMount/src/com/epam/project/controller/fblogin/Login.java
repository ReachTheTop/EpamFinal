package com.epam.project.controller.fblogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.command.Action;
import com.epam.project.db.model.Contact;
import com.epam.project.db.model.User;
import com.epam.project.db.service.ContactService;
import com.epam.project.db.service.UserService;
import com.epam.project.md5.SaltedMD5;
import com.epam.project.util.file.UploadFile;



public class Login implements Action {
	private String code="";
	public static final String FB_APP_ID = "1583492391912147";
	public static final String FB_APP_SECRET = "3675df614d24834893a33f7f47ddeb63";
	public static final String REDIRECT_URI = "http://localhost:8080/ITMount/FbLoginServlet?action=login";

	static String accessToken = "";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		code = request.getParameter("code");
		
		if (code == null || code.equals("")) {
			response.sendError(404);
			return;
		}
		
		String accessToken = getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
			String email = fbProfileData.get("email");
		User user =	UserService.getUserWhereEmail(email);
		if(user!=null){
			session.setAttribute("user", user);	
			response.sendRedirect("/ITMount/UserServlet");
			return;
		}else{
			User usr = new User();
			usr.setEmail(email);
			String key = SaltedMD5.getPassword(((Integer)new Random().nextInt(Integer.MAX_VALUE)).toString(), email) ;
			usr.setKey(key);
			usr.setName(fbProfileData.get("first_name"));
			usr.setSurname(fbProfileData.get("last_name"));
			usr.setRole_id(1);
			usr.setPassword_hash(SaltedMD5.getPassword(((Integer)new Random().nextInt(Integer.MAX_VALUE)).toString(), email));
			
			usr.setIs_confirmed(true);
			UserService.addNewUser(usr);
			usr = UserService.getUserWhereEmail(email);
			UploadFile upload = new UploadFile();
			String fileName = upload.uploadFileFromNET(fbProfileData.get("photo"), request.getServletContext(), "user_id"+usr.getId());
			usr.setImage(fileName);
			UserService.updateUser(usr);
			Contact contact = new Contact();
			contact.setUser_id(usr.getId());
			ContactService.addContact(contact);
			session.setAttribute("user", usr);
			response.sendRedirect("/ITMount/UserServlet");
			return;
		}
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "login";
	}
	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}
}
