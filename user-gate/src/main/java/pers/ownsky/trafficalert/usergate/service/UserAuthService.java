package pers.ownsky.trafficalert.usergate.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "ta-user-auth", path = "/auth")
public interface UserAuthService {
//    @GetMapping("/login")
//    public ResponseEntity<String> login(
//            @RequestParam("phone") String phone,
//            @RequestParam("password") String password);
//
//    @GetMapping("/logout")
//    public ResponseEntity<String> logout(
//            @RequestParam("token") String token);

    @GetMapping("check")
    public ResponseEntity<Map<String, Object>> check(
            @RequestParam("token") String token);

}
