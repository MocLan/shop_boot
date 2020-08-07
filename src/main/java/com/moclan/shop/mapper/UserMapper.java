package com.moclan.shop.mapper;


import com.moclan.shop.entity.Role;
import com.moclan.shop.entity.User;
import com.moclan.shop.model.request.UserRequest;
import com.moclan.shop.model.respone.RegisterResponse;
import com.moclan.shop.model.respone.RoleResponse;
import com.moclan.shop.model.respone.UserResponse;
import com.moclan.shop.repository.RoleRepository;
import com.moclan.shop.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private RoleMapper roleMapper;
    private RoleRepository roleRepository;

    @Autowired
    public UserMapper(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    public User transferToUser(UserRequest userRequest, User user) {
        BeanUtils.refine(userRequest, user, BeanUtils::copyNonNull);

        if (userRequest.getIds() != null) {
            user.setRoles(new HashSet<>());
            for (Long id : userRequest.getIds()) {
                Role role = roleRepository.getOne(id);
                user.getRoles().add(role);
            }
        }

        return user;
    }

    public UserResponse transferToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        BeanUtils.refine(user, userResponse, BeanUtils::copyNonNull);
        return userResponse;
    }

    public List<UserResponse> toUsersResponse(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(transferToUserResponse(user));
        }
        return userResponses;
    }

    public RegisterResponse transferToRegister(User user) {
        RegisterResponse registerResponse = new RegisterResponse();

        Set<RoleResponse> roleResponses = user.getRoles().stream()
                .map(roleMapper::transferToRoleResponse)
                .collect(Collectors.toSet());
        BeanUtils.refine(user, registerResponse, BeanUtils::copyNonNull);
        registerResponse.setRoles(roleResponses);
        return registerResponse;
    }
}
