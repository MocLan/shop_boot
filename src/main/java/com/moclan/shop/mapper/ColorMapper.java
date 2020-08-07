package com.moclan.shop.mapper;


import com.moclan.shop.entity.Color;
import com.moclan.shop.model.respone.ColorResponse;
import com.moclan.shop.util.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ColorMapper {


    public ColorResponse transferToColorResponse(Color color) {
        ColorResponse colorResponse = new ColorResponse();
        BeanUtils.refine(color, colorResponse, BeanUtils::copyNonNull);

        return colorResponse;
    }

    public Set<ColorResponse> transferToColorsResponse(Set<Color> colors) {
        Set<ColorResponse> colorResponses = new HashSet<>();
        for (Color color : colors) {
            colorResponses.add(transferToColorResponse(color));
        }
        return colorResponses;
    }
}
