package pers.ownsky.trafficalert.dataaccess.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends ModelBase {
    String name;
//    @JsonIgnore
    String password;
    @Column(unique = true, nullable = false)
    String phone;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    Date regDate;
    @OneToMany(mappedBy = "owner")
    List<CarPlate> cars;
}
