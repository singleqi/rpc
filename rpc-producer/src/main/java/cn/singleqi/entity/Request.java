package cn.singleqi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {

    private static final long serialVersionUID = 7929047349488932740L;

    private String id;

    private String className;// 类名

    private String methodName;// 函数名称

    private Class<?>[] parameterTypes;// 参数类型

    private Object[] parameters;// 参数列表

}
