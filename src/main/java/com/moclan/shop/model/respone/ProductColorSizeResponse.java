package com.moclan.shop.model.respone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductColorSizeResponse {
    private Long colorId;
    private Long sizeId;
    private Long amount;
}
