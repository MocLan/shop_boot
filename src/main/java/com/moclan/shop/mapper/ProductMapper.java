package com.moclan.shop.mapper;


import com.moclan.shop.entity.Color;
import com.moclan.shop.entity.Discount;
import com.moclan.shop.entity.Product;
import com.moclan.shop.entity.Size;
import com.moclan.shop.model.request.ProductRequest;
import com.moclan.shop.model.respone.BriefProductWebResponse;
import com.moclan.shop.model.respone.ProductResponse;
import com.moclan.shop.model.respone.ProductWebResponse;
import com.moclan.shop.repository.CategoryRepository;
import com.moclan.shop.repository.ColorRepository;
import com.moclan.shop.repository.DiscountRepository;
import com.moclan.shop.repository.SizeRepository;
import com.moclan.shop.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class ProductMapper {
    private ColorRepository colorRepository;
    private SizeRepository sizeRepository;
    private DiscountRepository discountRepository;
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private ProductColorSizeMapper colorSizeMapper;
    private ColorMapper colorMapper;
    private SizeMapper sizeMapper;
    private DiscountMapper discountMapper;

    @Autowired
    public ProductMapper(ColorRepository colorRepository, SizeRepository sizeRepository, DiscountRepository discountRepository, CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductColorSizeMapper colorSizeMapper, ColorMapper colorMapper, SizeMapper sizeMapper, DiscountMapper discountMapper) {
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.discountRepository = discountRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.colorSizeMapper = colorSizeMapper;
        this.colorMapper = colorMapper;
        this.sizeMapper = sizeMapper;
        this.discountMapper = discountMapper;
    }

    public Product transferToProduct(ProductRequest productRequest, Product product) {
        BeanUtils.refine(productRequest, product, BeanUtils::copyNonNull);

        if (productRequest.getSubCategoryId() != null) {
            product.setCategory(categoryRepository.getOne(productRequest.getSubCategoryId()));
        }
        if (productRequest.getColorIds() != null) {
            product.setColors(new HashSet<>());
            for (Long id : productRequest.getColorIds()) {
                Color color = colorRepository.getOne(id);
                product.getColors().add(color);
            }
        }
        if (productRequest.getDiscountIds() != null) {
            product.setDiscounts(new HashSet<>());
            for (Long id : productRequest.getDiscountIds()) {
                Discount discount = discountRepository.getOne(id);
                product.getDiscounts().add(discount);
            }
        }
        if (productRequest.getSizeIds() != null) {
            product.setSizes(new HashSet<>());
            for (Long id : productRequest.getSizeIds()) {
                Size size = sizeRepository.getOne(id);
                product.getSizes().add(size);
            }
        }

        return product;
    }

    public ProductResponse transferToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.refine(product, productResponse, BeanUtils::copyNonNull);

        if (product.getCategory() != null) {
            productResponse.setSubCategory(categoryMapper
                    .transferToCategoryResponse(product.getCategory()));
        }
        return productResponse;
    }

    public ProductWebResponse transferToProductWebResponse(Product product) {
        ProductWebResponse productResponse = new ProductWebResponse();
        BeanUtils.refine(product, productResponse, BeanUtils::copyNonNull);
        productResponse.setCategory(categoryMapper.transferToCategoryResponse(product.getCategory()));
        productResponse.setColors(colorMapper.transferToColorsResponse(product.getColors()));
        productResponse.setSizes(sizeMapper.transferToSizesResponse(product.getSizes()));
        productResponse.setProductColorSizes(colorSizeMapper.
                toSetProductColorSizeResponses(product.getProductColorSizes()));
        productResponse.setDiscounts(discountMapper.transferToDiscountsResponse(product.getDiscounts()));

        return productResponse;
    }

    public List<ProductResponse> transferToProductsResponse(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(transferToProductResponse(product));
        }
        return productResponses;
    }

    public BriefProductWebResponse transferToBriefProductWebResponse(Product product) {
        BriefProductWebResponse webResponse = new BriefProductWebResponse();
        BeanUtils.refine(product, webResponse, BeanUtils::copyNonNull);
        webResponse.setDiscount(discountMapper.transferToDiscountsResponse(product.getDiscounts()));
        return webResponse;
    }

    public List<BriefProductWebResponse> transferToBriefProductsWebResponse(List<Product> products) {
        List<BriefProductWebResponse> webResponses = new ArrayList<>();
        for (Product product : products) {
            webResponses.add(transferToBriefProductWebResponse(product));
        }
        return webResponses;
    }


}
