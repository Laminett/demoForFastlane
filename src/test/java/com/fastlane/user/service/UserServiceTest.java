package com.fastlane.user.service;

import com.fastlane.user.dto.UserResponse;
import com.fastlane.user.dto.UserSaveRequest;
import com.fastlane.user.dto.UserUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional()
@Rollback()
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test-data.sql"),
})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void createAndGetUser() {
        String id = "test";
        String password = "password";

        UserSaveRequest userSaveRequest = UserSaveRequest.builder()
                .id(id)
                .password(password)
                .build();

        UserResponse userSaverResponse = userService.save(userSaveRequest);
        assertThat(userSaverResponse.getId()).isNotNull();

        UserResponse userResponse = userService.getUserById(userSaverResponse.getId());

        assertThat(userResponse.getId()).isEqualTo(id);
        assertThat(userResponse.getPassword()).isEqualTo(password);
    }

    @Test
    public void updateUser() {
        String _id = "abc";
        String _password = "password";
        String _newPassword = "newpassword";

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setPassword(_password);
        userUpdateRequest.setNewPassword(_newPassword);

        UserResponse user = userService.update(_id, userUpdateRequest);

        UserResponse userResponse = userService.getUserById(user.getId());
        assertThat(userResponse.getPassword()).isEqualTo(_newPassword);
    }

    @Test
    public void getUsers() {
        Page<UserResponse> users = userService.getUsers(PageRequest.of(0, 10));
        assertThat(users.getTotalElements()).isGreaterThanOrEqualTo(1);

        users.forEach(item -> System.out.println(item.getId()));
    }

}
