package com.fastlane.user.service;

import com.fastlane.user.dto.UserResponse;
import com.fastlane.user.dto.UserSaveRequest;
import com.fastlane.user.dto.UserUpdateRequest;
import com.fastlane.user.entity.User;
import com.fastlane.user.exception.*;
import com.fastlane.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log
@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return userRepository.findAll(pageable).map(UserResponse::new);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new UserResponse(user);
    }

    @Transactional
    public UserResponse save(UserSaveRequest userSaveRequest) {

        userRepository.findById(userSaveRequest.getId()).ifPresent(user -> {
            throw new UserAlreadyExistsException(user.getId());
        });

        User user = userSaveRequest.toEntity();

        // Create user
        userRepository.save(user);

        return new UserResponse(user);
    }

    @Transactional
    public UserResponse update(String id, UserUpdateRequest userUpdateRequest) {

        String _password = userUpdateRequest.getPassword();
        String _newPassword = userUpdateRequest.getNewPassword();

        // 기존 비밀번호와 새로운 비밀번호가 같을 시 Exception.
        if (_password.equals(_newPassword)) {
            throw new DuplicatedPasswordException(_password, _newPassword);
        }

        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        User user = userRepository.findByIdAndPassword(id, _password)
                .orElseThrow(() -> new IncorrectPasswordException(_password));

        user.update(userUpdateRequest);

        return new UserResponse(user);
    }

    @Transactional
    public String delete(String id) {
        userRepository.delete(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));

        return id;
    }

}
