package com.moclan.shop.service;


import com.moclan.shop.model.respone.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> findAll();
}
