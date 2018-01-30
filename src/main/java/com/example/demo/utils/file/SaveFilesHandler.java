package com.example.demo.utils.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SaveFilesHandler {
	private static String UPLOADED_FOLDER = "C://temp//";
	
	 public static void saveUploadedFiles(List<MultipartFile> files) throws IOException {

	        for (MultipartFile file : files) {

	            if (file.isEmpty()) {
	                continue; //next pls
	            }

	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);

	        }

	    }
}
