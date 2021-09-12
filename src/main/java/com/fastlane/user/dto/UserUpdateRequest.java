package com.fastlane.user.dto;

import com.fastlane.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "password must not be blank.")
    @Size(min = 5, max = 20, message = "password must be 5-20 characters.")
    @Pattern(regexp = "[a-zA-z0-9]+")
    private String password;

    @NotBlank(message = "new password must not be blank.")
    @Size(min = 5, max = 20, message = "new password must be 5-20 characters.")
    @Pattern(regexp = "[a-zA-z0-9]+")
    private String newPassword;

}
