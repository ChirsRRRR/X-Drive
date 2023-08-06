package com.imooc.pan.server.modules.share.context;

import com.imooc.pan.server.modules.share.entity.RPanShare;
import com.imooc.pan.server.modules.share.vo.ShareDetailVO;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询分享详情的上下文实体对象
 */
@Data
public class QueryShareDetailContext implements Serializable {

    private static final long serialVersionUID = -5118135318932475703L;

    /**
     * 对应的分享ID
     */
    private Long shareId;

    /**
     * 分享实体
     */
    private RPanShare record;

    /**
     * 分享详情的VO对象
     */
    private ShareDetailVO vo;
}
