package com.company.interview.employeetask.security;

import com.company.interview.employeetask.service.serviceImpl.UserDetailsServiceImpl;
import com.company.interview.employeetask.utils.CommonMessageBundle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CommonMessageBundle messageBundle;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);

        if (!StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        } else if (tokenProvider.validateToken(jwt)) {
            try {
                refreshSession(request, jwt);
            } catch (Exception e) {
                log.debug(e.getMessage());
                throw new ServletException(messageBundle.getMessage("user.authentication.context.exception"));
            }
        } else {
            throw new ServletException(messageBundle.getMessage("token.invalid.exception"));
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            return token.equals("Invalid") ? null : token;
        }
        return null;
    }

    private String refreshToken() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String newToken = tokenProvider.generateToken(authentication);
            return "Bearer " + newToken;
        }
        return null;
    }

    private void refreshSession(HttpServletRequest request, String jwt) {
        Long userId = tokenProvider.getUserIdFromJWT(jwt);

        UserDetails userDetails = customUserDetailsService
                .loadUserById(userId);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities()
                );
        authentication.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request)
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
    }
}