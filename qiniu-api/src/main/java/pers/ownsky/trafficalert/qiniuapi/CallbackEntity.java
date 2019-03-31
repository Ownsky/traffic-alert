package pers.ownsky.trafficalert.qiniuapi;

import lombok.Data;

@Data
public class CallbackEntity {
    String key;
    String hash;
    String fsize;
}
