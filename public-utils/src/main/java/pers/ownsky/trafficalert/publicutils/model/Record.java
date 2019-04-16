package pers.ownsky.trafficalert.publicutils.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Record extends ModelBase {
    User uploader;
    Double lat;
    Double lng;
    String videoKey;
    String videoHash;
    Long videoSize;
    String audio;
    CarPlate toCar;
    Boolean checked;
}
