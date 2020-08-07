package com.moclan.shop.mapper;


import com.moclan.shop.entity.Cart;
import com.moclan.shop.model.request.CartRequest;
import com.moclan.shop.repository.ProductRepository;
import com.moclan.shop.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    private ProductRepository productRepository;

    @Autowired
    public CartMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Cart transferToCart(Cart cart, CartRequest cartRequest) {
        BeanUtils.refine(cartRequest, cart, BeanUtils::copyNonNull);

        return cart;
    }
}
