package com.ajinkyabhutkar.irctc.dto;

import org.springframework.data.domain.Page;

import java.util.List;


public record PagedResponse<T>(List<T> content, int page, int size, Long totalElements, int totalPages, boolean isLast) {


    public static<T> PagedResponse<T> fromPage(Page<T> page){

        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}

