package com.moclan.shop.entity.metamodel;


import com.moclan.shop.entity.Permission;
import com.moclan.shop.entity.Role;
import com.moclan.shop.entity.User;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Permission.class)
public class Permission_ {
    public static volatile SingularAttribute<Permission, User> userId;
    public static volatile SingularAttribute<Permission, Role> roleId;
}
