package com.app.validator;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ClienteValidationError {
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> errors = new ArrayList<>();
	
	private final String errorMessage;
	
	public ClienteValidationError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void addValidationErrors(String error) {
		errors.add(error);
	}
	
	public List<String> getErros(){
		return errors;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
