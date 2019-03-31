package pers.ownsky.trafficalert.publicutils.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class ModelBase {
    @JsonIgnore
    private Long id;
}
