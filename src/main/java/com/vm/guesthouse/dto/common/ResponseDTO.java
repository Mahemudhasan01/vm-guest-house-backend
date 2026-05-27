package com.vm.guesthouse.dto.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResponseDTO<T> implements Serializable {

	private Integer statusCode;

	private Boolean isError;

	private String message;

	private Object errorMessage;

	private T data;

	public static <T> ResponseDTO<T> success(T data, String message) {

		return ResponseDTO.<T>builder()
				.statusCode(200)
				.isError(false)
				.message(message)
				.data(data).build();
	}

	public static <T> ResponseDTO<T> error(String message) {

		return ResponseDTO.<T>builder()
				.statusCode(400)
				.isError(true)
				.message(message)
				.build();
	}
}