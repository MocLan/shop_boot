package com.moclan.shop.service;


import com.moclan.shop.model.request.IdsRequest;
import com.moclan.shop.model.request.UserFilterRequest;
import com.moclan.shop.model.request.UserRequest;
import com.moclan.shop.model.respone.RegisterResponse;
import com.moclan.shop.model.respone.UserFilterResponse;
import com.moclan.shop.model.respone.UserResponse;

import java.util.List;

public interface UserService {
    void insert(UserRequest userRequest);

    void update(Long id, UserRequest userRequest);

    void delete(Long id);

    void delete(IdsRequest ids);

    RegisterResponse findUserByUserName(String userName);

    List<UserResponse> findAll();

    UserFilterResponse findAllWithFilter(UserFilterRequest filterRequest);

    UserResponse findUserById(Long id);
}
