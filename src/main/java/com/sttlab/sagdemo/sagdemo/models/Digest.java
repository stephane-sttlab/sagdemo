package com.sttlab.sagdemo.sagdemo.models;

public class Digest {

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public DigestAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(DigestAlgorithm algorithm) {
		this.algorithm = algorithm;
	}


	private DigestAlgorithm algorithm;
	
	private String digest;

}
