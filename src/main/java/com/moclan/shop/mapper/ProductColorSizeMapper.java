package com.moclan.shop.mapper;


import com.moclan.shop.entity.ProductColorSize;
import com.moclan.shop.model.respone.ProductColorSizeResponse;
import com.moclan.shop.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProductColorSizeMapper {
    public ProductColorSizeResponse toProductColorSizeResponse(ProductColorSize productColorSize) {
        ProductColorSizeResponse response = new ProductColorSizeResponse();
        BeanUtils.refine(productColorSize, response, BeanUtils::copyNonNull);
        response.setColorId(productColorSize.getColor().getId());
        response.setSizeId(productColorSize.getSize().getId());
        return response;
    }

    public Set<ProductColorSizeResponse> toSetProductColorSizeResponses(Set<ProductColorSize> productColorSizes) {
        Set<ProductColorSizeResponse> productColorSizeResponses = new HashSet<>();
        for (ProductColorSize productColorSize : productColorSizes) {
            productColorSizeResponses.add(toProductColorSizeResponse(productColorSize));
        }
        return productColorSizeResponses;
    }
}
