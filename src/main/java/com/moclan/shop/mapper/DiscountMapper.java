package com.moclan.shop.mapper;


import com.moclan.shop.entity.Discount;
import com.moclan.shop.model.respone.DiscountResponse;
import com.moclan.shop.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DiscountMapper {
    public DiscountResponse transferToDiscountResponse(Discount discount) {
        Date date = new Date();
        if (date.after(discount.getStartDate()) && date.before(discount.getEndDate())) {
            DiscountResponse discountResponse = new DiscountResponse();
            BeanUtils.refine(discount, discountResponse, BeanUtils::copyNonNull);
            return discountResponse;
        }
        return null;
    }

    public Set<DiscountResponse> transferToDiscountsResponse(Set<Discount> discounts) {
        Set<DiscountResponse> discountResponses = new HashSet<>();
        for (Discount d : discounts) {
            if (transferToDiscountResponse(d) != null)
                discountResponses.add(transferToDiscountResponse(d));
        }
        return discountResponses;
    }
}
