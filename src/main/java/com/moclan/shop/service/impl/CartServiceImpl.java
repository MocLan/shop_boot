package com.moclan.shop.service.impl;


import com.moclan.shop.entity.Cart;
import com.moclan.shop.mapper.CartMapper;
import com.moclan.shop.model.request.CartRequest;
import com.moclan.shop.repository.CartRepository;
import com.moclan.shop.service.CartService;
import com.moclan.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ItemService itemService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.itemService = itemService;
    }

    @Override
    public void insert(CartRequest cartRequest) {
        Cart cart = new Cart();
        Date date = new Date();

        cart = cartMapper.transferToCart(cart, cartRequest);
        cart.setCreatedDate(new Timestamp(date.getTime()));
        cart.setStatus("NotContacted");
        cartRepository.save(cart);
        itemService.insert(cartRequest, cartRepository.getOne(cart.getId()));

    }

    @Override
    public void update(Long id, CartRequest cartRequest) {
        Cart cartExist = cartRepository.getOne(id);
        Cart newCart = cartMapper.transferToCart(cartExist, cartRequest);

        itemService.update(cartRequest, cartRepository.getOne(cartExist.getId()));
        cartRepository.save(newCart);
    }

    @Override
    public void delete(Long id) {
        Cart cart = cartRepository.getOne(id);
        cartRepository.delete(cart);
    }
}
