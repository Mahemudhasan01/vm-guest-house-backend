package com.vm.guesthouse.dto.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationUtils {

	public static Map<String, String> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getAllErrors().parallelStream().forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return errors;
    }
}
