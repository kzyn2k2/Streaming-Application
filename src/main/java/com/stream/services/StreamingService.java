package com.stream.services;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StreamingService {

	
	Resource getVideo(String title);
	
	String saveVideo(MultipartFile video) throws IOException;
}
