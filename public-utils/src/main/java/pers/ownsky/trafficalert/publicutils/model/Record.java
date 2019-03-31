package pers.ownsky.trafficalert.publicutils.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Record extends ModelBase {
    User uploader;
    String video;
    String audio;
    CarPlate toCar;
    Boolean checked;
}
