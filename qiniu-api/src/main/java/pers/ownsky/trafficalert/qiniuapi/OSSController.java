package pers.ownsky.trafficalert.qiniuapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController("/oss")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OSSController {
    private final OSSService ossService;

    public ResponseEntity<String> getUploadToken() {

    }

    public ResponseEntity<String> getDownloadToken() {
        return new ResponseEntity<>("not supported", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

    public ResponseEntity<String> uploadCallback() {

    }
}
