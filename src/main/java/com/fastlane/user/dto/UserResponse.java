package com.fastlane.user.dto;

import com.fastlane.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String id;

    private String password;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.password = entity.getPassword();
    }

    public UserResponse(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
