package com.imooc.pan.server.modules.share.context;

import com.imooc.pan.server.modules.share.entity.RPanShare;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateShareUrlContext implements Serializable {

    private static final long serialVersionUID = 2945253863400727173L;

    /**
     * 分享的名称
     */
    private String shareName;

    /**
     * 分享的类型
     */
    private Integer shareType;

    /**
     * 分享的日期类型
     */
    private Integer shareDayType;

    /**
     * 该分享对应的文件ID集合
     */
    private List<Long> shareFileIdList;

    /**
     * 当前登录用户的ID
     */
    private Long userId;

    /**
     * 已保存的分享实体信息
     */
    private RPanShare record;
}
