package pers.ownsky.trafficalert.userauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Response.Body;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import pers.ownsky.trafficalert.publicutils.json.RestError;
import pers.ownsky.trafficalert.publicutils.json.RestException;

import java.io.IOException;

@Configuration
public class RestErrorDecoder implements ErrorDecoder {
    @Override
//    @HystrixCommand(ignoreExceptions = {RestException.class})
    public RestException decode(String s, Response response) {
        if (response.status() == HttpStatus.OK.value()) return null;
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
