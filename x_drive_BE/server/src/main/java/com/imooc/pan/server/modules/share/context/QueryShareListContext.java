package com.imooc.pan.server.modules.share.context;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryShareListContext implements Serializable {

    private static final long serialVersionUID = -1719224070265640276L;

    /**
     * 当前登录的用户ID
     */
    private Long userId;
}
