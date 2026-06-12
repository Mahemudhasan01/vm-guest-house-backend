package com.vm.guesthouse.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.vm.guesthouse.constants.DatabaseConstants;
import com.vm.guesthouse.dto.PaginationSortingFilterSearchDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationUtils {

	//This method add because of Sorting...
    public static Pageable createPageable(PaginationSortingFilterSearchDTO request) {

        if (request.getSortBy() == null || request.getSortBy().isEmpty()) {
            return PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.by(Sort.Direction.DESC, DatabaseConstants.CREATED_DATE_TIME)
            );
        }

        String[] parts = request.getSortBy().split(",");

        String field = parts[0];
        Sort.Direction direction = Sort.Direction.fromString(parts[1]);

        return PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by(direction, field)
        );
    }
    
    public static List<Sort.Order> getSortDirection(Map<String, ?> sort) {
        List<Sort.Order> orders = new ArrayList<>();
        sort.forEach((s, o) -> {
            if (o.toString().equalsIgnoreCase("asc")) {
                orders.add(new Sort.Order(Sort.Direction.ASC, s));

            } else {
                orders.add(new Sort.Order(Sort.Direction.DESC, s));
            }
        });
        return orders;
    }

    public static Map<String, String> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getAllErrors().parallelStream().forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return errors;
    }
}
