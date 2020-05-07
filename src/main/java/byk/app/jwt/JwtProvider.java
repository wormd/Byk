package byk.app.jwt;

import byk.app.model.InternUser;
import byk.app.service.InternUserService;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.List;

import com.auth0.jwt.JWT;

@Component
public class JwtProvider {
    @Autowired
    static InternUserService intUserService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateTokenFromAuthentication(Authentication auth) {

        InternUser intUser = (InternUser) auth.getPrincipal();
        return JWT.create()
                .withSubject(intUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withArrayClaim("Roles",
                        intUser.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public InternUser parseToken(String token) {
        DecodedJWT decoded = JWT.require(Algorithm.HMAC512(secret.getBytes())).build().verify(token);
        InternUser intuser = new InternUser();
        intuser.setUsername(decoded.getSubject());
        Date expire = decoded.getExpiresAt();

        List<String> roles = decoded.getClaim("Roles").asList(String.class);
        intuser.setAuthorities(
            roles.stream()
            .map(i -> new SimpleGrantedAuthority(i))
            .collect(Collectors.toList()));
        return intuser;
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject();
    }
}
