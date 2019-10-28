package com.nerdysoft.testtask.web.service;

import com.nerdysoft.testtask.web.dto.UserSummary;
import com.nerdysoft.testtask.web.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserSummary getCurrentUser(UserPrincipal userPrincipal) {
        return UserSummary.builder()
                .id(userPrincipal.getId())
                .email(userPrincipal.getEmail())
                .name(userPrincipal.getName())
                .build();
    }
}
