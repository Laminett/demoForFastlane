package com.fastlane.user.controller;

import com.fastlane.user.dto.UserResponse;
import com.fastlane.user.dto.UserSaveRequest;
import com.fastlane.user.dto.UserUpdateRequest;
import com.fastlane.user.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Create User", notes = "회원 생성")
    @PostMapping("/api/users/create")
    public UserResponse save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        return userService.save(userSaveRequest);
    }

    @ApiOperation(value = "Update User", notes = "회원정보 변경(현재 비밀번호만 변경 가능)")
    @PostMapping("/api/users/{id}")
    public UserResponse update(@PathVariable String id, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest);
    }

    @ApiOperation(value = "Get Users", notes = "모든 회원 조회(10개 씩 페이징 적용)")
    @GetMapping("/api/users")
    public Page<UserResponse> getUsers() {
        return userService.getUsers(PageRequest.of(0, 10));
    }

    @ApiOperation(value = "Get User", notes = "회원 ID를 조건으로 회원정보 조회")
    @GetMapping("/api/users/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "Delete User", notes = "회원 삭제")
    @DeleteMapping("/api/users/{id}")
    public String delete(@PathVariable String id) {
        userService.delete(id);

        return id;
    }


}
