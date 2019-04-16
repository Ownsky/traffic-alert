package pers.ownsky.trafficalert.publicutils.model;

import lombok.Data;

@Data
public class OSSCallbackVo {
    String key;
    String hash;
    Long fsize;
    Long recid;
}
