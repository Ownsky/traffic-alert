package pers.ownsky.trafficalert.userauth;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pers.ownsky.trafficalert.publicutils.json.AuthExpiredException;
import pers.ownsky.trafficalert.publicutils.json.AuthFailException;
import pers.ownsky.trafficalert.publicutils.model.User;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final DataAccess dataAccess;
    private final StringRedisTemplate stringRedisTemplate;
    private final JWTUtil jwtUtil;

    String login(String phone, String password) {
        User user = validateUser(phone, password);
        String jwtToken = generateToken(user.getPhone());
        return jwtToken;
    }

    /**
     * @Description:
     * 1. check token in black list, if exist, auth fail
     * 2. check token expire status, if expire, auth fail
     * @author: Ownsky
     * @Param:  token
     * @Return: new token if old one is about to expire
     * @Thorws: AuthFailException
     * @Date:   2019/3/21
     */
    String check(String token) {
        if (token == null) {
            throw new AuthFailException("Unauthorized user");
        }
        DecodedJWT jwt = jwtUtil.verifyToken(token);
        String phone = jwt.getClaim("phone").asString();

        // check if token has been abandoned
        String sign = jwt.getSignature();
        String abandon = stringRedisTemplate.opsForValue().get(sign);
        if (abandon != null && abandon.equals(phone)) {
            throw new AuthExpiredException(phone);
        }

        Date expire = jwt.getExpiresAt();
        Date now = new Date();
        // already expire, auth fail
        if (expire.before(now)) {
            throw new AuthExpiredException(phone);
        }

        // about to expire, renew token
        if (expire.getTime() - now.getTime() < jwtUtil.refreshBefore) {
            token = generateToken(phone);
            return token;
        }
        return phone;
    }

    void logout(String token) {
        if (token == null) {
            throw new AuthFailException("Unauthorized user");
        }
        DecodedJWT jwt = jwtUtil.verifyToken(token);
        String phone = jwt.getClaim("phone").asString();
        String sign = jwt.getSignature();
        String abandon = stringRedisTemplate.opsForValue().get(sign);

        if (!phone.equals(jwt.getClaim("phone").asString())) {
            throw new AuthFailException("Unavailable token.");
        }

        if (abandon != null && abandon.equals(phone)) {
            throw new AuthExpiredException(phone);
        }

        Date now = new Date();
        Date expire = jwt.getExpiresAt();
        long diff = expire.getTime() - now.getTime();

        if (diff <= 0) {
            throw new AuthExpiredException(phone);
        }

        // add token to abandon list
        stringRedisTemplate.opsForValue().set(sign, phone, diff, TimeUnit.MILLISECONDS);
    }

    private User validateUser(String phone, String password) {
        ResponseEntity<User> res = dataAccess.validateUser(phone, password);//.getBody();
        User user = res.getBody();
        return user;
    }

    private String generateToken(String phone) {
        String jwtToken = jwtUtil.buildToken(phone);
//        stringRedisTemplate.opsForValue().set(
//                phone,
//                jwtToken,
//                jwtUtil.jwtExpireTime,
//                TimeUnit.MILLISECONDS);
        return jwtToken;
    }

}
