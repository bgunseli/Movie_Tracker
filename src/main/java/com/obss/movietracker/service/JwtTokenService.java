package com.obss.movietracker.service;

import com.obss.movietracker.security.JwtAuthenticationFilter;
import com.obss.movietracker.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JwtTokenService {

    private JwtTokenProvider jwtTokenProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public JwtTokenService(JwtTokenProvider jwtTokenProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    public Long getCurrentUserId(HttpServletRequest request) {
        return jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromCookie(request));
    }
}
