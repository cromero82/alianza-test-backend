package com.alianza.test.shared;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageablePrimitive {
    private int page;
    private int size;
    private String sortBy;
    private String sortOrder;
    public PageablePrimitive(int _page, int _size, String _sortBy, String _sortOrder){
        this.page = _page;
        this.size = _size;
        this.sortBy = _sortBy;
        this.sortOrder = _sortOrder;
    }
}
