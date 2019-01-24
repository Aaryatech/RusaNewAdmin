package com.ats.rusaadmin.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class VpsImageUpload {
	
	 
	
	//public static final String galleryImage= "/home/devour/apache-tomcat-9.0.12/webapps/mongistoreuploads/";
	public static final String galleryImage= "/home/lenovo/Documents/pdf/";
	 
	public void saveUploadedFiles(MultipartFile file, int imageType, String imageName) throws IOException {

		/*for (MultipartFile file : files) {

			if (file.isEmpty()) {

				System.out.println("int if");
				continue;

			}*/

			Path path = Paths.get(galleryImage + imageName);

			byte[] bytes = file.getBytes();

			if (imageType == 1) {
				System.out.println("Inside Image Type =1");

				path = Paths.get(galleryImage + imageName);

				System.out.println("Path= " + path.toString());

			}  

			Files.write(path, bytes);

		}

	//}

}
