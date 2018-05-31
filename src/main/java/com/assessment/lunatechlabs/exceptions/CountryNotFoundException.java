/**
 * 
 */
package com.assessment.lunatechlabs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Libu
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such country exist")
public class CountryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5807537603460503886L;

	public CountryNotFoundException(String message) {

		super(message);
	}
}
