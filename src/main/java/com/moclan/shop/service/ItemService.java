package com.moclan.shop.service;


import com.moclan.shop.entity.Cart;
import com.moclan.shop.model.request.CartRequest;

public interface ItemService {
    void insert(CartRequest cartRequest, Cart cart);

    void update(CartRequest cartRequest, Cart cart);
}
