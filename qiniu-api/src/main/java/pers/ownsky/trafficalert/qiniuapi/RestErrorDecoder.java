package pers.ownsky.trafficalert.qiniuapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import pers.ownsky.trafficalert.publicutils.json.RestError;
import pers.ownsky.trafficalert.publicutils.json.RestException;

import java.io.IOException;

@Configuration
public class RestErrorDecoder implements ErrorDecoder {
    @Override
//    @HystrixCommand(ignoreExceptions = {RestException.class})
    public RestException decode(String s, Response response) {
        if (response.status() / 100 == 2) return null;
        try {
            String body = Util.toString(response.body().asReader());
            ObjectMapper om = new ObjectMapper();
            RestError re =  om.readValue(body.getBytes("UTF-8"), RestError.class);
            RestException exception = new RestException(re.getStatus(), re.getMessage());
            return exception;
        } catch (IOException e) {
            return new RestException(e);
        }
    }
}
