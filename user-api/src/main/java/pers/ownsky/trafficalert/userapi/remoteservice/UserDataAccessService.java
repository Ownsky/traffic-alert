package pers.ownsky.trafficalert.userapi.remoteservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.publicutils.model.Record;
import pers.ownsky.trafficalert.publicutils.model.User;

import java.util.List;

@Service
@FeignClient(value = "ta-data-access")
public interface UserDataAccessService {
//    @GetMapping("/user/findByPhone")
//    ResponseEntity<User> getUserByPhone(@RequestParam("phone") String phone);

    @PostMapping("/user/save")
    ResponseEntity<User> saveUser(@RequestBody User user);

    //TODO
    @PostMapping("/record/preUpload")
    ResponseEntity<Long> newRecord(@RequestBody Record record);

    @GetMapping("/user/findByPhone")
    ResponseEntity<User> getUserByPhone(@RequestParam("phone") String phone);

    @GetMapping("/record/toCar/{cid}")
    ResponseEntity<List<Record>> notPushedToCar(@PathVariable("cid") Long cid);
}
