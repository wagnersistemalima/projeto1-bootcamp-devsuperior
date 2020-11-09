package com.sistemalima.dscatalog.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	// construtor
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
