package pers.ownsky.trafficalert.dataaccess.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.dataaccess.datasource.TargetDataSource;
import pers.ownsky.trafficalert.dataaccess.model.Test;
import pers.ownsky.trafficalert.dataaccess.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final TestRepository testRepository;
//    boolean which = true;

    @TargetDataSource("read")
    @GetMapping("/getById")
    public Optional<Test> getById(@RequestParam Long id) {
        Optional<Test> res;
        res = testRepository.findById(id);
        return res;
    }

    @TargetDataSource("main")
    @PostMapping("/saveData")
    public Test saveData(@RequestBody Test data) {
//        System.out.println(data);
//        return data;
//        Test res = new Test();
//        res.setData(data);
//        return res;
        return testRepository.save(data);
    }

    @TargetDataSource("main")
    @GetMapping("/getByData")
    public List<Test> getByData(@RequestParam String data) {
        return testRepository.findAllByData(data);
    }
}
