package com.example.movieapp.dto.dtoresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T>{

    private Integer page;
    private Integer size;
    private Integer totalPagesCount;
    private Long totalElementsCount;
    private List<T> items;
    public PageResponse(Integer page, Integer size, Integer totalPagesCount, Long totalElementsCount) {
        this.page = page;
        this.size = size;
        this.totalPagesCount = totalPagesCount;
        this.totalElementsCount = totalElementsCount;
    }
}
