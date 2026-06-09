package com.vm.guesthouse.exceptions;

import lombok.Data;

@Data
public class CustomExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

    private Integer statusCode = 400;

    private String customMessage = "Bad Request";

    /**
     * Instantiates a new Custom exceptions.
     *
     * @param errorMessage the error message
     */
    public CustomExceptions(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Instantiates a new Custom exceptions.
     *
     * @param errorMessage the error message
     * @param statusCode   the status code
     */
    public CustomExceptions(String errorMessage, Integer statusCode) {
        super(errorMessage);
        this.statusCode = statusCode;
    }

    /**
     * Instantiates a new Custom exceptions.
     *
     * @param errorMessage the error message
     * @param statusCode   the status code
     * @param message      the message
     */
    public CustomExceptions(String errorMessage, Integer statusCode, String message) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.customMessage = message;
    }
}
