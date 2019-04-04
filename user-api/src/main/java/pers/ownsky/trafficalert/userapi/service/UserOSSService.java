package pers.ownsky.trafficalert.userapi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(value = "ta-data-access", path = "/user")
public interface UserOSSService {
}
