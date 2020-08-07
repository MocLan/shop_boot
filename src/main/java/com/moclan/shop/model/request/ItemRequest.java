package com.moclan.shop.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private Long productId;
    private Long price;
    private Long amount;
}
