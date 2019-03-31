package pers.ownsky.trafficalert.userauth;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.ownsky.trafficalert.publicutils.json.RestException;
import pers.ownsky.trafficalert.publicutils.model.User;

//@Repository
@FeignClient(value = "ta-data-access", path = "/user")//, fallbackFactory = DataAccessFallback.class)
public interface DataAccess {
    @GetMapping("/findByPhone")
//    @HystrixCommand(ignoreExceptions = {RestException.class})
    ResponseEntity<User> getUserByPhone(@RequestParam("phone") String phone);

    @GetMapping("/validate")
//    @HystrixCommand(ignoreExceptions = {RestException.class})
    ResponseEntity<User> validateUser(@RequestParam("phone") String phone,
                             @RequestParam("password") String password);
}
