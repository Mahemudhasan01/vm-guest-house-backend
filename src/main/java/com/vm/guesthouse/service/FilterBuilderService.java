package com.vm.guesthouse.service;



import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vm.guesthouse.dto.FilterCondition;
import com.vm.guesthouse.dto.FilterOperationEnum;
import com.vm.guesthouse.exceptions.CustomExceptions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FilterBuilderService<T> {

    private static final int DEFAULT_SIZE_PAGE = 20;

    private static List<String> split(String search, String delimiter) {
        return Stream.of(search.split(delimiter))
                .collect(Collectors.toList());
    }

    /**
     * Prepare filter condition.  extract the different filters used in the controller via @RequestParam
     *
     * @param criteria search Criteria.
     * @return a list of {@link FilterCondition}
     */
    public List<FilterCondition> createFilterCondition(String criteria) {
        List<FilterCondition> filters = new ArrayList<>();

        try {

            if (criteria != null && !criteria.isEmpty()) {

                final String FILTER_SHEARCH_DELIMITER = "#&";
                final String FILTER_CONDITION_DELIMITER = "\\|";
                boolean flag = false;
                int index = 0;
                List<String> values = split(criteria, FILTER_SHEARCH_DELIMITER);
                if (!values.isEmpty()) {
                    for (String value : values) {
                        if (value.contains("sort")) {
                            flag = true;
                            index = values.indexOf(value);
                        }
                    }
                }
                if (flag) {
                    values.remove(index);
                }
                if (!values.isEmpty()) {
                    values.forEach(x -> {
                        List<String> filter = split(x, FILTER_CONDITION_DELIMITER);
                        if (FilterOperationEnum.fromValue(filter.get(1)) != null) {
                            if (filter.get(2).contains("true") || filter.get(2).contains("false"))
                                filters.add(new FilterCondition(filter.get(0), FilterOperationEnum.fromValue(filter.get(1)), Boolean.parseBoolean(filter.get(2))));
                            else if (filter.get(0).contains("date") || filter.get(0).contains("Date")) {
//                                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                                LocalDateTime localDateTime = LocalDateTime.parse(filter.get(2) + ServiceConstants.INITIAL_TIME, format);
                                LocalDate localDate = LocalDate.parse(filter.get(2));
                                LocalDateTime localDateTime = localDate.atStartOfDay();
                                filters.add(new FilterCondition(filter.get(0), FilterOperationEnum.fromValue(filter.get(1)), localDateTime));
                            } else
                                filters.add(new FilterCondition(filter.get(0), FilterOperationEnum.fromValue(filter.get(1)), filter.get(2)));
                        }
                    });
                }
            }

            return filters;

        } catch (Exception ex) {
            throw new CustomExceptions("Cannot create condition filter " + ex.getMessage());
        }

    }
    
	public static <T> Specification<T> buildSpecification(List<FilterCondition> andConditions,
			List<FilterCondition> orConditions) {
		Specification<T> spec = Specification.where(null);

		if (andConditions != null) {
			for (FilterCondition condition : andConditions) {
				spec = spec.and(buildSpecFromCondition(condition));
			}
		}

		if (orConditions != null && !orConditions.isEmpty()) {
			Specification<T> orSpec = buildSpecFromCondition(orConditions.get(0));
			for (int i = 1; i < orConditions.size(); i++) {
				orSpec = orSpec.or(buildSpecFromCondition(orConditions.get(i)));
			}
			spec = spec.and(orSpec);
		}

		return spec;
	}
	
	private static <T> Specification<T> buildSpecFromCondition(FilterCondition condition) {
        return (root, query, cb) -> {
            Path<Object> path = (Path<Object>) getPath(root, condition.getField());

            switch (condition.getOperator()) {
                case EQUAL:
                    return cb.equal(path, condition.getValue());
                case CONTAINS:
                    return cb.like(path.as(String.class), "%" + condition.getValue() + "%");
                case GREATER_THAN:
                    return cb.greaterThan(path.as(Comparable.class), (Comparable) condition.getValue());
                case LESS_THAN:
                    return cb.lessThan(path.as(Comparable.class), (Comparable) condition.getValue());
                case GREATER_THAN_OR_EQUAL_TO:
                	Class<?> fieldType = path.getJavaType();
                	
                	if (LocalDate.class.equals(fieldType)) {
                		return cb.greaterThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) condition.getValue());
                	} else if (Integer.class.equals(fieldType)) {
                	    return cb.greaterThanOrEqualTo(path.as(Integer.class), (Integer) condition.getValue());
                	}
                case LESSTHAN_OR_EQUAL_TO:
                	Class<?> fieldType1 = path.getJavaType();
                	if (LocalDate.class.equals(fieldType1)) {
                		return cb.lessThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) condition.getValue());
                	} else if (Integer.class.equals(fieldType1)) {
                		return cb.lessThanOrEqualTo(path.as(Integer.class), (Integer) condition.getValue());
                	}
                default:
                    throw new UnsupportedOperationException("Operator not supported: " + condition.getOperator());
            }
        };
    }
	
	 // Handles nested fields like "school.id"
    private static Path<?> getPath(Path<?> root, String fieldPath) {
        if (!fieldPath.contains(".")) {
            return root.get(fieldPath);
        }
        String[] parts = fieldPath.split("\\.");
        Path<?> path = root.get(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            path = path.get(parts[i]);
        }
        return path;
    }
    
	/* Not Working show attribute not located exception occured
	 * 
	 * public Specification<T> buildSpecification(List<FilterCondition> andFilters,
	 * List<FilterCondition> orFilters) { return (root, query, cb) -> {
	 * List<Predicate> andPredicates = new ArrayList<>(); for (FilterCondition fc :
	 * andFilters) { andPredicates.add(cb.equal(root.get(fc.getField()),
	 * fc.getValue())); }
	 * 
	 * List<Predicate> orPredicates = new ArrayList<>(); for (FilterCondition fc :
	 * orFilters) { orPredicates.add(cb.equal(root.get(fc.getField()),
	 * fc.getValue())); }
	 * 
	 * Predicate and = cb.and(andPredicates.toArray(new Predicate[0])); Predicate or
	 * = cb.or(orPredicates.toArray(new Predicate[0]));
	 * 
	 * return cb.and(and, or); }; }
	 */
    
    public Specification<T> buildSpecification(String andFilter, String orFilter) {
        return (root, query, cb) -> {
            List<Predicate> andPredicates = new ArrayList<>();
            if (andFilter != null && !andFilter.isEmpty()) {
                String[] parts = andFilter.split(":", 2);
                if (parts.length == 2) {
                    String field = parts[0];
                    String value = parts[1];
                    andPredicates.add(cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%"));
                }
            }

            List<Predicate> orPredicates = new ArrayList<>();
            if (orFilter != null && !orFilter.isEmpty()) {
                String[] parts = orFilter.split(":", 2);
                if (parts.length == 2) {
                    String field = parts[0];
                    String value = parts[1];
                    orPredicates.add(cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%"));
                }
            }

            Predicate and = andPredicates.isEmpty() ? cb.conjunction() : cb.and(andPredicates.toArray(new Predicate[0]));
            Predicate or = orPredicates.isEmpty() ? cb.conjunction() : cb.or(orPredicates.toArray(new Predicate[0]));

            return cb.and(and, or);
        };
    }

    
//    public Specification<NonTeachingActivity> createSpecification(List<FilterCondition> andFilters, List<FilterCondition> orFilters) {
//        return (root, query, cb) -> {
//            List<Predicate> andPredicates = andFilters.stream()
//                .map(fc -> buildPredicate(cb, root, fc))
//                .collect(Collectors.toList());
//
//            List<Predicate> orPredicates = orFilters.stream()
//                .map(fc -> buildPredicate(cb, root, fc))
//                .collect(Collectors.toList());
//
//            Predicate andCombined = cb.and(andPredicates.toArray(new Predicate[0]));
//            Predicate orCombined = cb.or(orPredicates.toArray(new Predicate[0]));
//
//            return cb.and(andCombined, orCombined); // Adjust if you want OR at top level
//        };
//    }
    
    private Predicate buildPredicate(CriteriaBuilder cb, Root<?> root, FilterCondition condition) {
        // Example logic
        Path<String> path = root.get(condition.getField()); // assuming String field
        switch (condition.getOperator()) {
            case EQUAL:
                return cb.equal(path, condition.getValue());
            case CONTAINS:
                return cb.like(path, "%" + condition.getValue() + "%");
            // Add more cases
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + condition.getOperator());
        }
    }


}
