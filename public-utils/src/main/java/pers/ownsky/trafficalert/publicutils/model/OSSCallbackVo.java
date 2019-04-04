package pers.ownsky.trafficalert.publicutils.model;

import lombok.Data;

@Data
public class OSSCallbackVo {
    String key;
    String hash;
    String fsize;
    Long recid;
}
