package com.imooc.pan.server.modules.user.context;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 重置用户密码上下文实体
 */
@ApiModel(value = "用户忘记密码-重置用户密码")
@Data
public class ResetPasswordContext implements Serializable {

    private static final long serialVersionUID = 6483482439489859204L;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户新密码
     */
    private String password;

    /**
     * 重置密码的token信息
     */
    private String token;


}
