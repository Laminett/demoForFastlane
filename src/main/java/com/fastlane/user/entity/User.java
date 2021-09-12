package com.fastlane.user.entity;

import com.fastlane.user.dto.UserUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "uix_id", columnList = "id", unique = true)
})
public class User {

    @Id
    @Column
    private String id;

    @Column
    private String password;

    @Builder
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        this.password = userUpdateRequest.getNewPassword();
    }

}
