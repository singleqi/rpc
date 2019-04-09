package cn.singleqi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String address;

}
