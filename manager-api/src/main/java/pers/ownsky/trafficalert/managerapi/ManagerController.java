package pers.ownsky.trafficalert.managerapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.publicutils.model.CarPlate;
import pers.ownsky.trafficalert.publicutils.model.Record;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManagerController {

    private final DataAccessService remoteService;

    static ArrayBlockingQueue<Record> recordList = new ArrayBlockingQueue<>(3);

    @GetMapping("record/unchecked")
    public ResponseEntity<Record> getRecord() {
        Record record = recordList.poll();
//        remoteService.getUserByPhone("123456");
        if (record == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(record, HttpStatus.OK);
        }
    }

    @GetMapping("carPlate/{place}/{num}")
    public ResponseEntity<CarPlate> getCarPlate(@PathVariable Integer place, @PathVariable String num) {
        return remoteService.getCarByNum(place, num);
    }

    @PostMapping("record/checked")
    public ResponseEntity<Record> checkRecord(@RequestBody Record record) {
        record.setChecked(true);
        record.setPushed(false);
        return remoteService.setRecord(record.getId(), record);
    }
}
