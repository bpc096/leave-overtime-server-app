package com.mii.server.models.dto.requests;

import java.util.Date;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String gender;
    private Date birthdate;
    private String address;
    private String email;
    private String phone;
}
