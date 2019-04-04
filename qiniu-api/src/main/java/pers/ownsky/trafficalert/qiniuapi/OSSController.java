package pers.ownsky.trafficalert.qiniuapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ownsky.trafficalert.publicutils.model.OSSCallbackVo;

@RestController("/oss")
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
        return new ResponseEntity<>("not supported", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

    @PostMapping("/uploadCallback")
    public ResponseEntity<String> uploadCallback(OSSCallbackVo ossCallbackVo) {

    }
}
