package com.obss.movietracker.model.user;

import lombok.Getter;

@Getter

public class UserLoginBody {
    private String username;
    private String password;
    private Role role;
}
