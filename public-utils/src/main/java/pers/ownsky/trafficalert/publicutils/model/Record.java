package pers.ownsky.trafficalert.publicutils.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


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
    String message;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    Date date;
    Boolean checked;
    Boolean pushed;
}
