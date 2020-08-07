package com.moclan.shop.mapper;


import com.moclan.shop.entity.Category;
import com.moclan.shop.model.respone.CategoryResponse;
import com.moclan.shop.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse transferToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.refine(category, categoryResponse, BeanUtils::copyNonNull);

        return categoryResponse;
    }
}
