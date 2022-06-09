package com.management.system.errors;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

/**
 * Utility class for ResponseEntity creation.
 */
public interface ResponseUtil {

	/**
	 * Wrap for optional.
	 *
	 * @param <X> the generic type
	 * @param maybeResponse the maybe response
	 * @return the response entity
	 */
	static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
		return wrapOrNotFound(maybeResponse, null);
	}

	/**
	 * Wrap or not found.
	 *
	 * @param <X> the generic type
	 * @param maybeResponse the maybe response
	 * @param header the header
	 * @return the response entity
	 */
	static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
		return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
