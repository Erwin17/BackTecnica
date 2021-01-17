package com.app.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ClienteValidatorErrorBuilder {

	public static ClienteValidationError fromBindingErrors(Errors errors) {
		
		ClienteValidationError clienteError = new ClienteValidationError(
				"Validacion fallida. " + errors.getErrorCount() + " error"
				);
		
		for(ObjectError objectError : errors.getAllErrors()) {
			clienteError.addValidationErrors(objectError.getDefaultMessage());
		}
		
		return clienteError;
	}
	
}
