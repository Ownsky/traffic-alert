package pers.ownsky.trafficalert.userservice;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ta-data-access", path = "/user")
public interface DataAccess {

}
