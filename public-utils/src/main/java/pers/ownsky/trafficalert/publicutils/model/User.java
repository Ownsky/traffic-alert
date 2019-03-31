package pers.ownsky.trafficalert.publicutils.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends ModelBase {
    String name;
    String password;
    String phone;
    Date regDate;
    List<CarPlate> cars;
}
