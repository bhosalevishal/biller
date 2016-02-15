package com.vb.biller.scanner.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class BillerInitUtil {

	public static final BasicFileAttributes getBasicAttributes(String path){
		Path jobPath = Paths.get(path);
		BasicFileAttributes basicAttributes = null;
		try {
			basicAttributes = Files.readAttributes(jobPath, BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return basicAttributes;
	}
	
	public static final String removeExtension(String fileName){
		String noExtension = fileName.replaceFirst("[.][^.]+$", "");
		return convertToCamelCase(noExtension);
	}
	
	public static final String convertToCamelCase(String stringToConvert){
		return stringToConvert;
	}
}
