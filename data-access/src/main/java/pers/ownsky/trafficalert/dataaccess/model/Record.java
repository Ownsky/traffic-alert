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
    String video;
    String audio;
    @ManyToOne
    CarPlate toCar;
    Boolean checked;
}
