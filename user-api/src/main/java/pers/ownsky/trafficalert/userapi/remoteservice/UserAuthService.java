package pers.ownsky.trafficalert.userapi.remoteservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "ta-user-auth", path = "/auth")
public interface UserAuthService {
    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password);

    @GetMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestParam("token") String token);

//    @GetMapping("check")
//    public ResponseEntity<String> check(
//            @RequestParam("token") String token);

}
