package com.moclan.shop.repository;


import com.moclan.shop.entity.Cart;
import com.moclan.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCart(Cart cart);
}
