package com.stream.model.dto;

import java.time.LocalDateTime;

public interface ViewTimeDTO {
	
	ViewDTO getView();
	
	LocalDateTime getViewedTime();
	
}
