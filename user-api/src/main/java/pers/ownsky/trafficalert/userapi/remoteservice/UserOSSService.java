package pers.ownsky.trafficalert.userapi.remoteservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = "ta-oss-api", path = "/oss")
public interface UserOSSService {
    @GetMapping("/uploadToken")
    public ResponseEntity<String> getUploadToken();
}
