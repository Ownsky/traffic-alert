package pers.ownsky.trafficalert.publicutils.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarPlate extends ModelBase {
    User owner;
    Integer place;
    String num;
}
