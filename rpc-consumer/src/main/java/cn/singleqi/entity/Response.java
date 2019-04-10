package cn.singleqi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {

    private static final long serialVersionUID = -1023480952777229650L;

    private String requestId;

    private int code;

    private String errorMsg;

    private Object data;
}
