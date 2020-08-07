package com.moclan.shop.model.respone;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilterResponse {
    List<ProductResponse> products;
    private Integer totalPages;
    private Integer currentPage;
}
