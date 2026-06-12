package com.vm.guesthouse.dto;


import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterOperationEnum {

    /**
     * Equal filter operation enum.
     */
    EQUAL("eq"),
    /**
     * Not equal filter operation enum.
     */
    NOT_EQUAL("neq"),
    /**
     * Greater than filter operation enum.
     */
    GREATER_THAN("gt"),
    /**
     * Greater than or equal to filter operation enum.
     */
    GREATER_THAN_OR_EQUAL_TO("gte"),
    /**
     * Less than filter operation enum.
     */
    LESS_THAN("lt"),
    /**
     * Lessthan or equal to filter operation enum.
     */
    LESSTHAN_OR_EQUAL_TO("lte"),
    /**
     * In filter operation enum.
     */
    IN("in"),
    /**
     * Not in filter operation enum.
     */
    NOT_IN("nin"),
    /**
     * Between filter operation enum.
     */
    BETWEEN("btn"),
    /**
     * Contains filter operation enum.
     */
    CONTAINS("like"),
    /**
     * Not contains filter operation enum.
     */
    NOT_CONTAINS("notLike"),
    /**
     * Is null filter operation enum.
     */
    IS_NULL("isnull"),
    /**
     * Is not null filter operation enum.
     */
    IS_NOT_NULL("isnotnull"),
    /**
     * Start with filter operation enum.
     */
    START_WITH("startwith"),
    /**
     * End with filter operation enum.
     */
    END_WITH("endwith"),
    /**
     * Is empty filter operation enum.
     */
    IS_EMPTY("isempty"),
    /**
     * Is not empty filter operation enum.
     */
    IS_NOT_EMPTY("isnotempty"),
    /**
     * Join filter operation enum.
     */
    JOIN("jn"),
    /**
     * Is filter operation enum.
     */
    IS("is");


    /**
     * The Value.
     */
    private final String value;

    /**
     * Instantiates a new Filter operation enum.
     *
     * @param value the value
     */
    FilterOperationEnum(String value) {
        this.value = value;
    }

    /**
     * From value filter operation enum.
     *
     * @param value the value
     * @return the filter operation enum
     */
    public static FilterOperationEnum fromValue(String value) {
        for (FilterOperationEnum op : FilterOperationEnum.values()) {

            //Case insensitive operation name
            if (String.valueOf(op.value).equalsIgnoreCase(value)) {
                return op;
            }
        }
        return null;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

}