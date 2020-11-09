package com.sistemalima.dscatalog.service.exceptions;

public class DataBaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	// construtor 
	
	public DataBaseException(String msg) {
		super(msg);
	}

}
