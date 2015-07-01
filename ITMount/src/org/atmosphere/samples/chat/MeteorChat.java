/*
 * Copyright 2015 Async-IO.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.atmosphere.samples.chat;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.atmosphere.config.service.MeteorService;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.Meteor;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Simple Servlet that implement the logic to build a Chat application using
 * a {@link Meteor} to suspend and broadcast chat message.
 *
 * @author Jeanfrancois Arcand
 */
@MeteorService(path = "/GroupServlet/meteor/{room: [a-zA-Z][a-zA-Z_0-9]*}", interceptors = {AtmosphereResourceLifecycleInterceptor.class})
public class MeteorChat extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private BroadcasterFactory broadcasterFactory;

    /**
     * Create a {@link Meteor} and use it to suspend the response.
     *
     * @param req An {@link HttpServletRequest}
     * @param res An {@link HttpServletResponse}
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Set the logger level to TRACE to see what's happening.
        Meteor.build(req).addListener(new AtmosphereResourceEventListenerAdapter());
    }

    /**
     * Re-use the {@link Meteor} created on the first GET for broadcasting message.
     *
     * @param req An {@link HttpServletRequest}
     * @param res An {@link HttpServletResponse}
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String body = req.getReader().readLine().trim();
        // Simple JSON -- Use Jackson for more complex structure
        // Message looks like { "author" : "foo", "message" : "bar" }
        HashMap<String, String> data = new ObjectMapper().readValue(body, HashMap.class);
        
        /*String author = body.substring(body.indexOf(":") + 2, body.indexOf(",") - 1);
        String message = body.substring(body.lastIndexOf(":") + 2, body.length() - 2);*/
        /*List<String> path = new ArrayList<String>(Arrays.asList(url.split("/")));*/
        String url = req.getRequestURI().substring(req.getContextPath().length());
      
        url = url.replaceAll("meteor/", "meteor/meteor/");
        
        
        System.out.println(url);
        
        String author = data.get("author");
        String message = data.get("message");
        String image = data.get("image");
        broadcasterFactory.lookup(url).broadcast(new Data(image, author, message).toString());
    }

    private final static class Data {

        private final String text;
        private final String author;
        private final String image;
        public Data(String image, String author, String text) {
            this.author = author;
            this.text = text;
            this.image = image;
        }

        public String toString() {
        	JSONObject json = new JSONObject();
        	try {
				json.put("image", image);
				json.put("text", text);
				json.put("author", author);
				json.put("time", new Date().getTime());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         /*   return "{ \"image\" : \""+image+"\", \"text\" : \"" + text + "\", \"author\" : \"" + author + "\" , \"time\" : " + new Date().getTime() + "}";*/
        	return json.toString();
        }
    }
}
