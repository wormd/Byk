package byk.app.jwt;


import byk.app.model.InternUser;
import byk.app.service.InternUserService;
import byk.app.CustomRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.exceptions.SignatureVerificationException;

@Component
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            if (token.startsWith("Bearer ")) {
                try {
                    InternUser intUser = jwt.parseToken(token.substring(7));
                    Authentication auth = new UsernamePasswordAuthenticationToken(
                        intUser.getUsername(), null, intUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (JwtNotFoundUsernameException e) {
                    System.out.println("Username not found");
                } catch (TokenExpiredException | JWTDecodeException | BadCredentialsException e) {
                    System.out.println(e);
                } catch (SignatureVerificationException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Incomplete token");
            }
        }
        chain.doFilter(request, response);
    }
}
