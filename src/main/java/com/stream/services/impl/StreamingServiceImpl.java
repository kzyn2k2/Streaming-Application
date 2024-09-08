package com.stream.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stream.services.StreamingService;


@Service
public class StreamingServiceImpl implements StreamingService {

	
	private static final String FORMAT = "classpath:videos/%s.mp4";
	
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Override
	public Resource getVideo(String title) {
		return resourceLoader.getResource(String.format(FORMAT, title));
	}


	@Override
	public String saveVideo(MultipartFile video) throws IOException {
		
		String fileName = StringUtils.cleanPath(video.getOriginalFilename());
		String dir = "src/main/resources/videos/";
		Path path = Paths.get(dir+fileName);
		try(InputStream inputStream = video.getInputStream()) {
			Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
		} 
		return FilenameUtils.removeExtension(fileName);
	}
	
}
