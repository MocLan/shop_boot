package com.moclan.shop.service.impl;

import com.moclan.shop.entity.User;
import com.moclan.shop.mapper.RoleMapper;
import com.moclan.shop.mapper.UserMapper;
import com.moclan.shop.model.request.IdsRequest;
import com.moclan.shop.model.request.UserFilterRequest;
import com.moclan.shop.model.request.UserRequest;
import com.moclan.shop.model.respone.RegisterResponse;
import com.moclan.shop.model.respone.RoleResponse;
import com.moclan.shop.model.respone.UserFilterResponse;
import com.moclan.shop.model.respone.UserResponse;
import com.moclan.shop.repository.RoleRepository;
import com.moclan.shop.repository.UserRepository;
import com.moclan.shop.service.UserService;
import com.moclan.shop.specification.UserSpecification;
import com.moclan.shop.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;
    private RoleMapper roleMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, BCryptPasswordEncoder encoder, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.roleMapper = roleMapper;
    }

    @Override
    public void insert(UserRequest userRequest) {
        Date date = new Date();
        User user = userMapper.transferToUser(userRequest, new User());

        user.setCreatedDate(new Timestamp(date.getTime()));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedBy(userRequest.getCreator());

        userRepository.save(user);
    }

    @Override
    public void update(Long id, UserRequest userRequest) {
        Date date = new Date();
        Optional<User> userExist = userRepository.findById(id);
        User newUser = userMapper.transferToUser(userRequest, userExist.get());

        newUser.setPassword(encoder.encode(userExist.get().getPassword()));
        newUser.setModifiedDate(new Timestamp(date.getTime()));
        newUser.setModifiedBy(userRequest.getCreator());

        userRepository.save(newUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.getOne(id));
    }

    @Override
    public void delete(IdsRequest ids) {
        for (Long id : ids.getIds()) {
            delete(id);
        }
    }


    @Override
    public RegisterResponse findUserByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        return userMapper.transferToRegister(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return userMapper.toUsersResponse(userRepository.findAll());
    }

    @Override
    public UserFilterResponse findAllWithFilter(UserFilterRequest filterRequest) {
        UserFilterResponse userFilterResponse = new UserFilterResponse();

        List<User> users = userRepository.findAll(UserSpecification.filterUser(filterRequest)
                , PageRequest.of(
                        filterRequest.getPage()
                        , filterRequest.getSize()
                        , sort(filterRequest.getSort())
                )).getContent();

        Long countAllUser = userRepository.count(UserSpecification.filterUser(filterRequest));
        int total = (int) Math.ceil((double) countAllUser / filterRequest.getSize());
        userFilterResponse.setCurrentPage(filterRequest.getPage());
        userFilterResponse.setTotalPages(total);
        userFilterResponse.setUsers(userMapper.toUsersResponse(users));
        return userFilterResponse;
    }


    private Sort sort(String typeDateSort) {
        if (typeDateSort != null) {
            if (typeDateSort.equals("date-des")) {
                return Sort.by("createdDate").descending();
            } else if (typeDateSort.equals("date-asc")) {
                return Sort.by("createdDate").ascending();
            }
        }
        return Sort.by("createdDate").descending();
    }

    @Override
    public UserResponse findUserById(Long id) {
        User user = userRepository.getOne(id);
        UserResponse userResponse = new UserResponse();
        Set<RoleResponse> roleResponses = user.getRoles()
                .stream()
                .map(roleMapper::transferToRoleResponse)
                .collect(Collectors.toSet());
        userResponse.setRoles(roleResponses);
        BeanUtils.refine(user, userResponse, BeanUtils::copyNonNull);
        return userResponse;
    }

}
