package pers.ownsky.trafficalert.dataaccess.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CarPlate extends ModelBase {

    @JsonIgnore
    @ManyToOne() //(fetch= FetchType.LAZY)
    User owner;
    Integer place;
    String num;
}
