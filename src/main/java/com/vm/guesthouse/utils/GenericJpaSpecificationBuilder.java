package com.vm.guesthouse.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vm.guesthouse.dto.FilterCondition;

public class GenericJpaSpecificationBuilder<T> {

    private Specification<T> spec;

    public Specification<T> build(List<FilterCondition> andConditions, List<FilterCondition> orConditions) {
        Specification<T> spec = Specification.where(null);

        // Apply AND conditions
        if (andConditions != null) {
            for (FilterCondition condition : andConditions) {
                spec = spec.and(getSpecification(condition));
            }
        }

        // Apply OR conditions
        if (orConditions != null) {
            for (FilterCondition condition : orConditions) {
                spec = spec.or(getSpecification(condition));
            }
        }

        return spec;
    }

    private Specification<T> getSpecification(FilterCondition condition) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Path<?> path = getPath(root, condition.getField());

            switch (condition.getOperator()) {
                case EQUAL:
                    return cb.equal(path, condition.getValue());
                case NOT_EQUAL:
                    return cb.notEqual(path, condition.getValue());
                case CONTAINS:
                    return cb.like(cb.lower(path.as(String.class)),
                                   "%" + condition.getValue().toString().toLowerCase() + "%");
                case GREATER_THAN:
                    return cb.greaterThan(path.as(Comparable.class), (Comparable) condition.getValue());
                case LESS_THAN:
                    return cb.lessThan(path.as(Comparable.class), (Comparable) condition.getValue());
                case IN:
                    return path.in((List<?>) condition.getValue());
                    
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
                    throw new UnsupportedOperationException("Unsupported operator: " + condition.getOperator());
            }
        };
    }
    
    
    //*************** Old one ***************
    // Handles nested fields like "school.id"
//    private static Path<?> getPath(Path<?> root, String fieldPath) {
//        if (!fieldPath.contains(".")) {
//            return root.get(fieldPath);
//        }
//        String[] parts = fieldPath.split("\\.");
//        Path<?> path = root.get(parts[0]);
//        for (int i = 1; i < parts.length; i++) {
//            path = path.get(parts[i]);
//        }
//        return path;
//    }
    
    private static Path<?> getPath(From<?, ?> root, String fieldPath) {
        String[] parts = fieldPath.split("\\.");

        From<?, ?> from = root;
        Path<?> path = null;

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            if (i < parts.length - 1) {
                // Intermediate path → must be joinable
                try {
                    // Try to join if it's a collection
                    from = from.join(part, JoinType.LEFT);
                } catch (IllegalArgumentException ex) {
                    // If it's not a collection, just navigate
                    from = (From<?, ?>) from.get(part);
                }
            } else {
                // Last element → actual field
                path = from.get(part);
            }
        }

        return path;
    }



    public GenericJpaSpecificationBuilder<T> withAndConditions(List<FilterCondition> conditions) {
        for (FilterCondition condition : conditions) {
            spec = (spec == null) ? toSpecification(condition) : spec.and(toSpecification(condition));
        }
        return this;
    }

    public GenericJpaSpecificationBuilder<T> withOrConditions(List<FilterCondition> conditions) {
        for (FilterCondition condition : conditions) {
            spec = (spec == null) ? toSpecification(condition) : spec.or(toSpecification(condition));
        }
        return this;
    }

    public Specification<T> build() {
        return spec;
    }

    private Specification<T> toSpecification(FilterCondition condition) {
        return (root, query, cb) -> {
            Path<Object> path = (Path<Object>) getPath(root, condition.getField());
            Object value = condition.getValue();

            switch (condition.getOperator().toString().toLowerCase()) {
                case "eq":
                    return cb.equal(path, value);
                case "like":
                    return cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
                case "gt":
                    return cb.greaterThan(path.as(Comparable.class), (Comparable) value);
                case "lt":
                    return cb.lessThan(path.as(Comparable.class), (Comparable) value);
                default:
                    return null;
            }
        };
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Specification<T> of(FilterCondition condition) {
        return (root, query, cb) -> {
            Path<?> path = getPath(root, condition.getField());

            switch (condition.getOperator()) {
                case EQUAL:
                    return cb.equal(path, condition.getValue());
                case NOT_EQUAL:
                    return cb.notEqual(path, condition.getValue());
                case GREATER_THAN:
                    return cb.greaterThan(path.as(Comparable.class), (Comparable) condition.getValue());
                case LESS_THAN:
                    return cb.lessThan(path.as(Comparable.class), (Comparable) condition.getValue());
                case CONTAINS:
                    return cb.like(cb.lower(path.as(String.class)), "%" + condition.getValue().toString().toLowerCase() + "%");
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

    public Specification<T> addConditions(List<FilterCondition> andConditions, List<FilterCondition> orConditions) {
        return (root, query, cb) -> {
        	query.distinct(true);
            List<Predicate> andPredicates = new ArrayList<>();
            List<Predicate> orPredicates = new ArrayList<>();

            if (andConditions != null && !andConditions.isEmpty()) {
                for (FilterCondition condition : andConditions) {
                    andPredicates.add(buildPredicate(condition, root, cb));
                }
            }

            if (orConditions != null && !orConditions.isEmpty()) {
                for (FilterCondition condition : orConditions) {
                    orPredicates.add(buildPredicate(condition, root, cb));
                }
            }

            Predicate finalPredicate = cb.conjunction();

            if (!andPredicates.isEmpty()) {
                finalPredicate = cb.and(finalPredicate, cb.and(andPredicates.toArray(new Predicate[0])));
            }
            if (!orPredicates.isEmpty()) {
                finalPredicate = cb.and(finalPredicate, cb.or(orPredicates.toArray(new Predicate[0])));
            }

            return finalPredicate;
        };
    }


    private Predicate buildPredicate(FilterCondition condition, Root<T> root, CriteriaBuilder cb) {
        Path<Object> path = (Path<Object>) getPath(root, condition.getField());

        switch (condition.getOperator()) {
            case EQUAL:
                return cb.equal(path, condition.getValue());
            case CONTAINS:
                return cb.like(cb.lower(path.as(String.class)),
                               "%" + condition.getValue().toString().toLowerCase() + "%");
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
    }

    public static List<Predicate> buildAndPredicates(CriteriaBuilder cb, Root<?> root,
                                                     List<FilterCondition> filterConditions) {
        List<Predicate> predicates = new ArrayList<>();

        if (filterConditions != null) {
            for (FilterCondition condition : filterConditions) {
                Path<?> path = getPath(root, condition.getField());
                Object value = condition.getValue();

                switch (condition.getOperator()) {
                    case EQUAL:
                        predicates.add(cb.equal(path, value));
                        break;
                    case CONTAINS:
                        predicates.add(cb.like(path.as(String.class), "%" + value + "%"));
                        break;
                    case GREATER_THAN:
                        predicates.add(cb.greaterThan(path.as(Comparable.class), (Comparable) value));
                        break;
                    case LESS_THAN:
                        predicates.add(cb.lessThan(path.as(Comparable.class), (Comparable) value));
                        break;
                        
                    case GREATER_THAN_OR_EQUAL_TO:
                    	Class<?> fieldType = path.getJavaType();
                    	
                    	if (LocalDate.class.equals(fieldType)) {
                    		cb.greaterThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) condition.getValue());
                    		break;
                    	} else if (Integer.class.equals(fieldType)) {
                    	    cb.greaterThanOrEqualTo(path.as(Integer.class), (Integer) condition.getValue());
                    	    break;
                    	}
                    case LESSTHAN_OR_EQUAL_TO:
                    	Class<?> fieldType1 = path.getJavaType();
                    	if (LocalDate.class.equals(fieldType1)) {
                    		cb.lessThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) condition.getValue());
                    		break;
                    	} else if (Integer.class.equals(fieldType1)) {
                    		cb.lessThanOrEqualTo(path.as(Integer.class), (Integer) condition.getValue());
                    		break;
                    	}
                    default:
                        throw new UnsupportedOperationException("Unsupported operator: " + condition.getOperator());
                }
            }
        }

        return predicates;
    }

    public static List<Predicate> buildOrPredicates(CriteriaBuilder cb, Root<?> root,
                                                    List<FilterCondition> filterConditions) {
        List<Predicate> predicates = new ArrayList<>();

        if (filterConditions != null) {
            for (FilterCondition condition : filterConditions) {
                Path<?> path = getPath(root, condition.getField());
                Object value = condition.getValue();

                switch (condition.getOperator()) {
                    case EQUAL:
                        predicates.add(cb.equal(path, value));
                        break;
                    case CONTAINS:
                        predicates.add(cb.like(path.as(String.class), "%" + value + "%"));
                        break;
                    case GREATER_THAN:
                        predicates.add(cb.greaterThan(path.as(Comparable.class), (Comparable) value));
                        break;
                    case LESS_THAN:
                        predicates.add(cb.lessThan(path.as(Comparable.class), (Comparable) value));
                        break;
                    case GREATER_THAN_OR_EQUAL_TO:
                    	Class<?> fieldType = path.getJavaType();
                    	
                    	if (LocalDate.class.equals(fieldType)) {
                    		cb.greaterThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) condition.getValue());
                    		break;
                    	} else if (Integer.class.equals(fieldType)) {
                    	    cb.greaterThanOrEqualTo(path.as(Integer.class), (Integer) condition.getValue());
                    	    break;
                    	}
                    case LESSTHAN_OR_EQUAL_TO:
                    	Class<?> fieldType1 = path.getJavaType();
                    	if (LocalDate.class.equals(fieldType1)) {
                    		cb.lessThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) condition.getValue());
                    		break;
                    	} else if (Integer.class.equals(fieldType1)) {
                    		cb.lessThanOrEqualTo(path.as(Integer.class), (Integer) condition.getValue());
                    		break;
                    	}
                    default:
                        throw new UnsupportedOperationException("Unsupported operator: " + condition.getOperator());
                }
            }
        }

        return predicates;
    }

    public static <T> Specification<T> build(List<FilterCondition> conditions, boolean isOr) {
        Specification<T> spec = Specification.where(null);

        for (FilterCondition condition : conditions) {
            Specification<T> conditionSpec = buildSpecFromCondition(condition);
            if (isOr) {
                spec = spec.or(conditionSpec);
            } else {
                spec = spec.and(conditionSpec);
            }
        }

        return spec;
    }
}