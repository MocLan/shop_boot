package com.moclan.shop.service;


import com.moclan.shop.model.request.IdsRequest;
import com.moclan.shop.model.request.ProductAdminRequest;
import com.moclan.shop.model.request.ProductRequest;
import com.moclan.shop.model.request.ProductWebFilterRequest;
import com.moclan.shop.model.respone.BriefProductFilterResponse;
import com.moclan.shop.model.respone.ProductFilterResponse;
import com.moclan.shop.model.respone.ProductResponse;
import com.moclan.shop.model.respone.ProductWebResponse;

import java.util.List;

public interface ProductService {
    void insert(ProductRequest productRequest);

    void update(Long id, ProductRequest productRequest);

    void delete(Long id);

    void delete(IdsRequest ids);

    List<ProductResponse> findAll();

    ProductFilterResponse findAllWithFilter(ProductAdminRequest filterRequest);

    ProductResponse findById(Long id);

    ProductWebResponse findByIdWeb(Long id);

    BriefProductFilterResponse findBriefProducts(ProductWebFilterRequest filterRequest);
}
