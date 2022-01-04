package com.sttlab.sagdemo.sagdemo.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ApiError {

	public ApiError() {
		super();
		this.dateTime = new Date();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	private UUID id;
	private Date dateTime;
	private String httpStatus;
	private String errorCode;
	private List<String> errorMessages;

}
