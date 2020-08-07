package com.moclan.shop.entity.metamodel;


import com.moclan.shop.entity.Permission;
import com.moclan.shop.entity.Role;
import com.moclan.shop.entity.User;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {
    public static volatile SingularAttribute<Role, Long> id;
    public static volatile SetAttribute<Role, User> users;
    public static volatile SetAttribute<Role, Permission> permissions;
}
