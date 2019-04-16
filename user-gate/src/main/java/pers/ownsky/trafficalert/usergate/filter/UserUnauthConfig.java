package pers.ownsky.trafficalert.usergate.filter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("zuul.routes.user-api")
public class UserUnauthConfig {

//    @Value("${.path}")
    String path;

//    @Value("${zuul.routes.user-api.unauth-api}")
    List<String> unauthApi;

    boolean unauthable(String uri) {
        if (unauthApi == null) return false;
        for (String uapi :
                unauthApi) {
            String uuri = path.replace("**", uapi);
            if (uuri.equalsIgnoreCase(uri))
                return true;
        }
        return false;
    }
}
