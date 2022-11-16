package com.crud.service;

public class ResourceNotFoundException extends Exception {
 private String string;
	public ResourceNotFoundException(String string) {
		this.string=string;
	}

}
