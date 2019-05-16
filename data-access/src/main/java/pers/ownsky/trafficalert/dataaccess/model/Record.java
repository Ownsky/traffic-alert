package pers.ownsky.trafficalert.dataaccess.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Record extends ModelBase {
    @ManyToOne()
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
    String message;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    Date date;
    Boolean checked;
    Boolean pushed;
}
