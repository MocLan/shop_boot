package com.moclan.shop.service;


import com.moclan.shop.model.request.CartRequest;

public interface CartService {
    void insert(CartRequest cartRequest);

    void update(Long id, CartRequest cartRequest);

    void delete(Long id);


}
