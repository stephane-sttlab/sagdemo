package com.sttlab.sagdemo.sagdemo.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DigestRequest {

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DigestAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(DigestAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@NotNull(message="algorithm must not be null")
	private DigestAlgorithm algorithm;
	
	@NotNull(message="text is required")
	@Size(min=1, message="text must not be empty")
	private String text;

}
