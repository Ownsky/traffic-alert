package pers.ownsky.trafficalert.managerapi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.ownsky.trafficalert.publicutils.model.Record;

import java.util.concurrent.TimeUnit;

@Service
@EnableBinding(Sink.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MQListener {

    private final DataAccessService remoteService;

    @StreamListener(Sink.INPUT)
    public void onReceive(byte[] msg) {
        String msgText = new String(msg);
        Long msgInt = Long.parseLong(msgText);
        Record record = remoteService.getRecord(msgInt).getBody();
        try {
            ManagerController.recordList.put(record);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(msgText);

    }
}
