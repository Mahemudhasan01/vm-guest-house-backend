package com.vm.guesthouse.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import lombok.Data;

@Data
public class PaginationSortingFilterSearchDTO implements Serializable {

    private Map<String, ?> filter;

    private Map<String, ?> sort;

    private String searchKey;

    private String filterAnd;

    private String filterOr;

    private int page = 0;

    private int size = 10;
    
    private String sortBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationSortingFilterSearchDTO that = (PaginationSortingFilterSearchDTO) o;
        return page == that.page &&
                size == that.size &&
                Objects.equals(filter, that.filter) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(searchKey, that.searchKey) &&
                Objects.equals(filterAnd, that.filterAnd) &&
                Objects.equals(filterOr, that.filterOr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter, sort, searchKey, filterAnd, filterOr, page, size);
    }

    @Override
    public String toString() {
        return "PaginationSortingFilterSearchDTO{" +
                "filter=" + filter +
                ", sort=" + sort +
                ", searchKey='" + searchKey + '\'' +
                ", filterAnd='" + filterAnd + '\'' +
                ", filterOr='" + filterOr + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

}