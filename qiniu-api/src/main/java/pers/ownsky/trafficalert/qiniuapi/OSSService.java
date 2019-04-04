package pers.ownsky.trafficalert.qiniuapi;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OSSService {

    @Value("${oss.accessKey}")
    String accessKey;

    @Value("${oss.secretKey}")
    String secretKey;

    @Value("${oss.bucket}")
    String bucket;

    @Value("$(oss.callbackUrl)")
    String callbackUrl;

    @Value("${oss.expire}")
    long expireSeconds;

    String getUploadAuthToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", callbackUrl);
        putPolicy.put("callbackBody", "{" +
                "\"key\":\"$(key)\"," +
                "\"hash\":\"$(etag)\"," +
                "\"fsize\":$(fsize)," +
                "\"recid\":$(x:recid)" +
                "}");
        putPolicy.put("callbackBodyType", "application/json");
//        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
//        System.out.println(upToken);
        return upToken;
    }



}
