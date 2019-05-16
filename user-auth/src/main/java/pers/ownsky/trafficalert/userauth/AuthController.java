package pers.ownsky.trafficalert.userauth;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ownsky.trafficalert.publicutils.json.RestException;
import pers.ownsky.trafficalert.publicutils.model.User;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(String phone, String password) {
        String token = userService.login(phone, password);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(String token) {
        userService.logout(token);

        return new ResponseEntity<>("logout success", HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> check(String token) {
        Map<String, Object> result = userService.check(token);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
