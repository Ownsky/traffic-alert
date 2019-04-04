package pers.ownsky.trafficalert.userapi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pers.ownsky.trafficalert.publicutils.model.User;

@Service
@FeignClient(value = "ta-data-access")
public interface UserDataAccessService {
//    @GetMapping("/user/findByPhone")
//    ResponseEntity<User> getUserByPhone(@RequestParam("phone") String phone);

    @PostMapping("/user/save")
    ResponseEntity<User> saveUser(@RequestBody User user);

    @GetMapping("/preUpload")
    ResponseEntity<Long> preUpload(@RequestParam("phone") String phone);
}
