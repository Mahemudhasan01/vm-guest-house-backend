package com.vm.guesthouse.dto.service;

import com.vm.guesthouse.dto.PageableResponse;
import com.vm.guesthouse.dto.PaginationSortingFilterSearchDTO;

public interface BaseService<D> {

	D save(D dto);

    D update(D dto);

    D get(Long id);

    Boolean delete(Long id);

    PageableResponse<D> getAll(PaginationSortingFilterSearchDTO paginationSortingFilterSearchDTO);
}
