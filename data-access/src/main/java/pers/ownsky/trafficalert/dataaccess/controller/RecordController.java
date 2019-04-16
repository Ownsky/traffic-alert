package pers.ownsky.trafficalert.dataaccess.controller;

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
    @GetMapping("/preUpload")
    public ResponseEntity<Long> newRecord(@RequestBody Record record) {
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
}
