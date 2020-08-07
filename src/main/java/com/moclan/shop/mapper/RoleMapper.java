package com.moclan.shop.mapper;

import com.moclan.shop.entity.Role;
import com.moclan.shop.model.respone.RoleResponse;
import com.moclan.shop.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse transferToRoleResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        BeanUtils.refine(role, roleResponse, BeanUtils::copyNonNull);
        return roleResponse;
    }
}
