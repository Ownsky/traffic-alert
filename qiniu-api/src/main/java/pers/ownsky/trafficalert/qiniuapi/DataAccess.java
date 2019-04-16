package pers.ownsky.trafficalert.qiniuapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pers.ownsky.trafficalert.publicutils.model.OSSCallbackVo;
import pers.ownsky.trafficalert.publicutils.model.User;

//@Repository
@FeignClient(value = "ta-data-access", path = "/record")//, fallbackFactory = DataAccessFallback.class)
public interface DataAccess {
    @PostMapping("/postUpload")
    ResponseEntity<String> postUpload(@RequestBody OSSCallbackVo callback);
}
