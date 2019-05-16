package pers.ownsky.trafficalert.qiniuapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class MQSendService {
    @Autowired
    private Source source;

    public void send(String msg) {
//        try {
        source.output().send(MessageBuilder.withPayload(msg).build());
//        }
    }
}
