package pers.ownsky.trafficalert.publicutils.json;

import lombok.Data;

@Data
public class RestError {
    Integer status;
    String message;
}
