package com.moclan.shop.entity.metamodel;


import com.moclan.shop.entity.Permission;
import com.moclan.shop.entity.Role;
import com.moclan.shop.entity.User;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SetAttribute<User, Role> roles;
    public static volatile SetAttribute<User, Permission> permissions;
}
