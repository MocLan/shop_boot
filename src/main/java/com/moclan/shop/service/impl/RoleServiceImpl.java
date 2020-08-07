package com.moclan.shop.service.impl;


import com.moclan.shop.mapper.RoleMapper;
import com.moclan.shop.model.respone.RoleResponse;
import com.moclan.shop.repository.RoleRepository;
import com.moclan.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::transferToRoleResponse)
                .collect(Collectors.toList());
    }
}
