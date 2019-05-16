package pers.ownsky.trafficalert.qiniuapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.ownsky.trafficalert.publicutils.model.OSSCallbackVo;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OSSController {
    private final OSSService ossService;


    @GetMapping("/uploadToken")
    public ResponseEntity<String> getUploadToken() {
        String upToken = ossService.getUploadAuthToken();
        return new ResponseEntity<>(upToken, HttpStatus.OK);
    }

    @GetMapping("/downloadToken")
    public ResponseEntity<String> getDownloadToken() {
        return new ResponseEntity<>("not supported", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping("/upload/callback")
    public ResponseEntity<String> uploadCallback(@RequestBody OSSCallbackVo ossCallbackVo) {
        //TODO: after upload
        System.out.println("!");
        System.out.println(ossCallbackVo.getKey());

//        try {
            ossService.postUpload(ossCallbackVo);
//        } catch (Exception ignored) {}
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

//    @PostMapping("/upload/callback")
//    public ResponseEntity<String> uploadCallback2(HttpServletRequest request) {
//        System.out.println("!!!");
//        return new ResponseEntity<>("OK", HttpStatus.OK);
//    }
}
