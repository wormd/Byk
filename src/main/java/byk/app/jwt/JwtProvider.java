package byk.app.jwt;

import byk.app.model.InternUser;
import byk.app.model.User;
import byk.app.service.InternUserService;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Collection;
import java.util.Map;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtProvider {
    @Autowired
    static InternUserService intUserService;

    @Value("${jwt.secret}")
    private String secret = "secretttt";

    @Value("${jwt.expiration}")
    private int expiration = 36000;

    public String generateTokenFromAuthentication(Authentication auth) {

        InternUser intUser = (InternUser) auth.getPrincipal();

        return JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withArrayClaim("Role", (String[])
                        intUser.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toArray())
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public InternUser parseToken(String token) {
        try {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC512(secret.getBytes())).build().verify(token.substring(7));
            InternUser user = (InternUser) intUserService.loadUserByUsername(decoded.getSubject());
            Date expire = decoded.getExpiresAt();
            // intUser.setAuthorities((Collection<? extends GrantedAuthority>) decoded.getClaim("Role"));
            return user;

        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject();
    }

}
