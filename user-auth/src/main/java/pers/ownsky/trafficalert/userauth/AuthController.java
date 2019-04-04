package pers.ownsky.trafficalert.userauth;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ownsky.trafficalert.publicutils.json.RestException;
import pers.ownsky.trafficalert.publicutils.model.User;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(String phone, String password) {
        String token = userService.login(phone, password);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(String phone, String token) {
        userService.logout(phone, token);
        return new ResponseEntity<>("logout success", HttpStatus.OK);
    }

    @GetMapping("check")
    public ResponseEntity<String> check(String phone, String token) {
        String newt = userService.check(phone, token);
        if (newt == null) {
            return new ResponseEntity<>("check passed", HttpStatus.OK);
        }
        return new ResponseEntity<>(newt, HttpStatus.CREATED);
    }

}
