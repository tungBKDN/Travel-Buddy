package com.travelbuddy.common.mapper;

import com.travelbuddy.common.paging.PageDto;
import com.travelbuddy.common.paging.PaginationDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PageMapper {
    public <T> PageDto<T> toPageDto(Page<T> page){
        List<T> data = page.getContent();
        PaginationDto paginationDto = PaginationDto.builder()
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalItems((int)page.getTotalElements())
                .itemsPerPage(page.getSize())
                .build();
        return new PageDto<>(data, paginationDto);
    }
}
