package pers.ownsky.trafficalert.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.publicutils.json.RestException;
import pers.ownsky.trafficalert.publicutils.model.Record;
import pers.ownsky.trafficalert.publicutils.model.User;
import pers.ownsky.trafficalert.userapi.controller.VO.Coordinate;
import pers.ownsky.trafficalert.userapi.remoteservice.UserDataAccessService;
import pers.ownsky.trafficalert.userapi.remoteservice.UserOSSService;
import pers.ownsky.trafficalert.userapi.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceController {
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> login(String phone, String password) {
        return new ResponseEntity<>(
                userService.login(phone, password),
                HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return new ResponseEntity<>(
                userService.logout(token),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.register(user),
                HttpStatus.OK);
    }

    @PostMapping("/preUpload")
    public ResponseEntity<Map<String, Object>> preUpload(
            @RequestBody Coordinate coordinate,
            HttpServletRequest request) {
        String phone = (String) request.getHeader("phone");
        return new ResponseEntity<>(
                userService.preUpload(phone, coordinate.getLat(), coordinate.getLng()),
                HttpStatus.OK
        );
    }

    @GetMapping("/notPushed")
    public ResponseEntity<List<Record>> notPushed(HttpServletRequest request) {
        String phone = (String) request.getHeader("phone");
        List result = userService.notPushed(phone);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hi(HttpServletRequest request) {
        String phone = (String) request.getHeader("phone");
        return new ResponseEntity<>("hi, "+phone, HttpStatus.OK);
    }

}
