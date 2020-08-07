package com.moclan.shop.service.impl;

import com.moclan.shop.entity.Cart;
import com.moclan.shop.entity.Item;
import com.moclan.shop.entity.Product;
import com.moclan.shop.model.request.CartRequest;
import com.moclan.shop.model.request.ItemRequest;
import com.moclan.shop.repository.ItemRepository;
import com.moclan.shop.repository.ProductRepository;
import com.moclan.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private ProductRepository productRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ProductRepository productRepository, ItemRepository itemRepository) {
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public void insert(CartRequest cartRequest, Cart cart) {
        for (ItemRequest itemRequest : cartRequest.getItems()) {
            Optional<Product> product = productRepository
                    .findById(itemRequest.getProductId());
            Item item = new Item(cart, product.get()
                    , itemRequest.getPrice(), itemRequest.getAmount());

            itemRepository.save(item);
        }
    }

    @Override
    public void update(CartRequest cartRequest, Cart cart) {
        List<Item> items = itemRepository.findByCart(cart);
        for (Item i : items) {
            itemRepository.delete(i);
        }
        insert(cartRequest, cart);
    }
}
