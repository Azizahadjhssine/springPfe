package com.app.exceptions;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public class ObjectValidationException extends RuntimeException{
	private final Set<String> errorMessages;

    private final String validationSource;


}
