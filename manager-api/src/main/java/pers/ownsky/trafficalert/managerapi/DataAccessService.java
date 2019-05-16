package pers.ownsky.trafficalert.managerapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.publicutils.model.CarPlate;
import pers.ownsky.trafficalert.publicutils.model.Record;
import pers.ownsky.trafficalert.publicutils.model.User;

@Service
@FeignClient(value = "ta-data-access")
public interface DataAccessService {

    @GetMapping("/record/{id}")
    ResponseEntity<Record> getRecord(@PathVariable("id") Long id);

    @PostMapping("/record/{id}")
    ResponseEntity<Record> setRecord(@PathVariable("id") Long id, @RequestBody Record record);

    @GetMapping("/user/findByPhone")
    ResponseEntity<User> getUserByPhone(@RequestParam("phone") String phone);

    @GetMapping("/carPlate/{place}/{num}")
    ResponseEntity<CarPlate> getCarByNum(@PathVariable("place") Integer place, @PathVariable("num") String num);
}