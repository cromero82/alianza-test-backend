package com.alianza.test.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultSearchData<T> {
    private List<T> content;
    private int totalPages;
    private int size;
    private long totalElements;
}
