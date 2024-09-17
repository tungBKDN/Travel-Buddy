package com.travelbuddy.common.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDto<T> {
    private List<T> data;
    private PaginationDto pagination;
}
