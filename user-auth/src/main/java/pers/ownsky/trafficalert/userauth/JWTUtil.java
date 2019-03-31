package pers.ownsky.trafficalert.userauth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.ownsky.trafficalert.publicutils.json.AuthFailException;

import java.util.Date;

@Data
@Component
public class JWTUtil {

    final String jwtSecret;

    final Long jwtExpireTime;

    final Long refreshBefore;

    public JWTUtil(
            @Value("${jwt.secret}")
            String jwtSecret,
            @Value("${jwt.expiration}")
            Long jwtExpireTime,
            @Value("${jwt.refresh.before}")
            Long refreshBefore
    ) {
        this.jwtSecret = jwtSecret;
        this.jwtExpireTime = jwtExpireTime;
        this.refreshBefore = refreshBefore;
    }

    String buildToken(String phone) {
        Date now = new Date();
        Algorithm alg = Algorithm.HMAC256(jwtSecret);
        String token = JWT.create()
                .withIssuer("traffic-alert")
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime()+jwtExpireTime))
                .withClaim("phone", phone)
                .sign(alg);
        return token;
    }

    /**
     * @Description:    return phone if token is legal
     * @author: Ownsky
     * @Param:  jwt token
     * @Return: phone
     * @Thorws:
     * @Date:   2019/3/19
     */
    DecodedJWT verifyToken(String token) {
        Algorithm alg = Algorithm.HMAC256(jwtSecret);
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new AuthFailException("Unavailable token.");
        }
        return jwt;
    }

}
