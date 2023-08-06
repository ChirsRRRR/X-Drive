package com.imooc.pan.server.modules.user.context;

import com.imooc.pan.server.modules.user.entity.RPanUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户在线修改密码上下文实体
 */
@Data
@ApiModel(value = "用户在线修改密码")
public class ChangePasswordContext implements Serializable {

    /**
     * 当前登录的用户ID
     */
    private Long UserId;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 当前用户的实体信息
     */
    private RPanUser entity;
}
