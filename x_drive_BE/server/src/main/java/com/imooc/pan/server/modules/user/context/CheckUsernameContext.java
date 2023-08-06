package com.imooc.pan.server.modules.user.context;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

/**
 * 检验用户名称PO对象
 */
@ApiModel(value = "用户忘记密码-检验用户名参数")
@Data
public class CheckUsernameContext implements Serializable {



    private static final long serialVersionUID = -7117844539768126736L;

    /**
     * 用户名称
     */
    private String username;
}
