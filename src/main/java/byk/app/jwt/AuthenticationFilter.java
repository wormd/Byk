package byk.app.jwt;


import byk.app.jwt.JwtAuthenticationException;
import byk.app.jwt.JwtProvider;
import byk.app.model.InternUser;
import byk.app.service.InternUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    InternUserService internUserService;

    @Autowired
    AuthenticationManager authMngr;

    @Autowired
    JwtProvider jwt;


    public AuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            throw new JwtAuthenticationException("Incomplete token");
        }
        InternUser intUser = jwt.parseToken(token);
        if (intUser != null) {
            Authentication authentication = authMngr.authenticate(
                    new UsernamePasswordAuthenticationToken(intUser.getUsername(), intUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
