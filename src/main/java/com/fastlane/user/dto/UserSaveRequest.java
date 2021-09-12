package com.fastlane.user.dto;

import com.fastlane.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
public class UserSaveRequest {

    @NotBlank(message = "id must not be blank.")
    @Size(min = 5, max = 20, message = "id must be 5-20 characters.")
    @Pattern(regexp = "[a-zA-z0-9]+")
    private String id;

    @NotBlank(message = "password must not be blank.")
    @Size(min = 5, max = 20, message = "password must be 5-20 characters.")
    @Pattern(regexp = "[a-zA-z0-9]+")
    private String password;

    @Builder
    public UserSaveRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .build();
    }
}
