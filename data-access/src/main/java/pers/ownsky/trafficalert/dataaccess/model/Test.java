package pers.ownsky.trafficalert.dataaccess.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Test extends ModelBase {
    String data;

}
