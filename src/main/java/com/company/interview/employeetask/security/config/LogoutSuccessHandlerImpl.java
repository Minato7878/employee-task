package com.company.interview.employeetask.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandlerImpl extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final String AUTHENTICATION_TOKEN_HEADER = "Authentication";

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        response.setHeader(AUTHENTICATION_TOKEN_HEADER, "Invalid");
        super.onLogoutSuccess(request, response, authentication);
    }
}