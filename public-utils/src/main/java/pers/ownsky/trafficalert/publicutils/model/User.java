package pers.ownsky.trafficalert.publicutils.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends ModelBase {
    String name;
//    @JsonIgnore
    String password;
    String phone;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    Date regDate;
    List<CarPlate> cars;
}
