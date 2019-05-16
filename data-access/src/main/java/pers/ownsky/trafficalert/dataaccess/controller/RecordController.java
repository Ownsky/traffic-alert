package pers.ownsky.trafficalert.dataaccess.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.dataaccess.datasource.TargetDataSource;
import pers.ownsky.trafficalert.dataaccess.model.Record;
import pers.ownsky.trafficalert.dataaccess.model.User;
import pers.ownsky.trafficalert.dataaccess.repository.RecordRepository;
import pers.ownsky.trafficalert.dataaccess.repository.UserRepository;
import pers.ownsky.trafficalert.publicutils.json.RestException;
import pers.ownsky.trafficalert.publicutils.json.UserNotFoundException;
import pers.ownsky.trafficalert.publicutils.model.OSSCallbackVo;

import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordController {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    /**
     * @Description:    db process before record uploaded into oss
     * @author: Ownsky
     * @Param:
     * @Return: record id
     * @Thorws:
     * @Date:   2019/4/1
     */
    @TargetDataSource("main")
    @PostMapping("/preUpload")
    public ResponseEntity<Long> newRecord(@RequestBody Record record) {
        User user = userRepository.findByPhone(record.getUploader().getPhone());
        record.setUploader(user);
        record = recordRepository.save(record);
        return new ResponseEntity<>(record.getId(), HttpStatus.CREATED);
    }

    @TargetDataSource("main")
    @PostMapping("/postUpload")
    public ResponseEntity<String> postUpload(@RequestBody OSSCallbackVo callback) {
        Record record = recordRepository.findById(callback.getRecid()).orElse(null);
        if (record == null) {
            throw new RestException("no such record");
        }
        record.setVideoKey(callback.getKey());
        record.setVideoHash(callback.getHash());
        record.setVideoSize(callback.getFsize());
        recordRepository.save(record);
        return new ResponseEntity<>("callback ok", HttpStatus.OK);
    }

    @TargetDataSource("read")
    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecord(@PathVariable Long id) {
        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            throw new RestException(404, "Record not found.");
        }
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @TargetDataSource("main")
    @PostMapping("/{id}")
    public ResponseEntity<Record> setRecord(@PathVariable Long id, @RequestBody Record record) {
//        Record previous = recordRepository.findById(id).orElse(null);
        record = recordRepository.save(record);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @TargetDataSource("read")
    @GetMapping("/toCar/{cid}")
    public ResponseEntity<List<Record>> notPushedToCar(@PathVariable Long cid) {
        List<Record> records = recordRepository.findByToCarIdAndPushed(cid, false);
//        for (Record)
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
}
