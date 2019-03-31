package pers.ownsky.trafficalert.dataaccess.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CarPlate extends ModelBase {
    @ManyToOne //(fetch= FetchType.LAZY)
    User owner;
    Integer place;
    String num;
}
