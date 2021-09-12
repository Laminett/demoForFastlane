package com.fastlane.user.controller;

import com.fastlane.user.dto.UserResponse;
import com.fastlane.user.dto.UserSaveRequest;
import com.fastlane.user.dto.UserUpdateRequest;
import com.fastlane.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users/create")
    public UserResponse save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        return userService.save(userSaveRequest);
    }

    @PostMapping("/api/users/{id}")
    public UserResponse update(@PathVariable String id, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest);
    }

    @GetMapping("/api/users")
    public Page<UserResponse> getUsers() {
        return userService.getUsers(PageRequest.of(0, 10));
    }

    @GetMapping("/api/users/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/api/users/{id}")
    public String delete(@PathVariable String id) {
        userService.delete(id);

        return id;
    }


}
