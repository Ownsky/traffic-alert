package pers.ownsky.trafficalert.dataaccess.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Record extends ModelBase {
    @ManyToOne
    User uploader;
    Double lat;
    Double lng;
//    String video;
    String videoKey;
    String videoHash;
    Long videoSize;
    String audio;
    @ManyToOne
    CarPlate toCar;
    Boolean checked;
}
