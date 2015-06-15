package com.epam.project.util.file;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;


public class DeleteFile {

	public static void deleteFile(String pathFile, ServletContext servletContext) throws IOException{
		String filePath = servletContext.getRealPath("") + File.separator + pathFile;
        File file = new File(filePath);
        file.delete();
	}
}
