package com.moclan.shop.mapper;


import com.moclan.shop.entity.Size;
import com.moclan.shop.model.respone.SizeResponse;
import com.moclan.shop.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SizeMapper {
    public SizeResponse transferToSizeResponse(Size size) {
        SizeResponse sizeResponse = new SizeResponse();
        BeanUtils.refine(size, sizeResponse, BeanUtils::copyNonNull);
        return sizeResponse;
    }

    public Set<SizeResponse> transferToSizesResponse(Set<Size> sizes) {
        Set<SizeResponse> sizeResponses = new HashSet<>();
        for (Size s : sizes) {
            sizeResponses.add(transferToSizeResponse(s));
        }
        return sizeResponses;
    }
}
