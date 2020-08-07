package com.moclan.shop.model.request;

import com.moclan.shop.validation.NOTBLANK;
import lombok.Data;

@Data
public class UserRequest {
    @NOTBLANK
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private boolean sex;
    private boolean status;
    private String creator;
    private Long[] ids;
}
