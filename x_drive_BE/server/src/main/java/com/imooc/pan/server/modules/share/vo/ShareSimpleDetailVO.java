package com.imooc.pan.server.modules.share.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("查询分享简单详情返回实体对象")
@Data
public class ShareSimpleDetailVO implements Serializable {

    private static final long serialVersionUID = -244174348108049506L;

    @ApiModelProperty("分享ID")
    private Long shareId;

    @ApiModelProperty("分享名称")
    private String shareName;

    @ApiModelProperty("分享人信息")
    private ShareUserInfoVO shareUserInfoVO;
}
