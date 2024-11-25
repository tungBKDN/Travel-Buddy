package com.travelbuddy.common.paging;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaginationDto {
    private int currentPage;
    private int totalPages;
    private int totalItems;
    private int itemsPerPage;
}
