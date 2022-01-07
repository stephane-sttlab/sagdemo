package com.sttlab.sagdemo.sagdemo.controllers;

import java.nio.charset.StandardCharsets;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;
import com.sttlab.sagdemo.sagdemo.models.Digest;
import com.sttlab.sagdemo.sagdemo.models.DigestRequest;


@RestController
@RequestMapping(path = "/digests")
public class DigestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping()			// Maps to POST /digests
	public ResponseEntity<Digest> createUser(@Valid @RequestBody DigestRequest body) {
		
		logger.debug("Text received: " + body.getText());
		logger.debug("Algorithm received: " + body.getAlgorithm());
		
		String digest = null;
		
		switch(body.getAlgorithm()){
		   
	       case SHA256: 
				digest = Hashing.sha256()
				  .hashString(body.getText(), StandardCharsets.UTF_8)
				  .toString();	
	           break;
	   
	       case SHA512:
				digest = Hashing.sha512()
				  .hashString(body.getText(), StandardCharsets.UTF_8)
				  .toString();	
	           break;
	   }
		
		logger.debug("Digest: " + digest);
		
		Digest resp = new Digest();
		resp.setAlgorithm(body.getAlgorithm());
		resp.setDigest(digest);

		return ResponseEntity.ok().body(resp);
	}

}
