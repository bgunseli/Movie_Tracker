package com.obss.movietracker.security;

public class LoggedInUser {

    private static String jwtToken;

    public static String getJwtToken() {
        return jwtToken;
    }

    public static void setJwtToken(String jwtToken) {
        LoggedInUser.jwtToken = jwtToken;
    }
}
