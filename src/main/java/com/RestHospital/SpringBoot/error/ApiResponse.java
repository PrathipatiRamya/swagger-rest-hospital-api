package com.RestHospital.SpringBoot.error;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

	private String Message;
	private int statusCode;
	private LocalDateTime timseStamp;
	
}
