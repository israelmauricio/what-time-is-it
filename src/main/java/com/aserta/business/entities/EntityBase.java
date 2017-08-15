package com.aserta.business.entities;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class EntityBase {
	public void validate() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		if (!validator.validate(this).isEmpty()) {
			throw new ValidationException();
		}
	}
}
