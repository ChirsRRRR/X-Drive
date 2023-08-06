package com.imooc.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户查询搜索历史记录上下文实体
 */
@Data
public class QueryUserSearchHistoryContext implements Serializable {

    private static final long serialVersionUID = -3357583223970424066L;

    /**
     * 当前登录的用户ID
     */
    private Long userId;
}
