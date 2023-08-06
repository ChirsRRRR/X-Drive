package com.imooc.pan.server.modules.share.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.pan.web.serializer.IdEncryptSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "创建分享链接的返回实体对象")
@Data
public class RPanShareUrlVO implements Serializable {

    private static final long serialVersionUID = -8175332883876974571L;

    @JsonSerialize(using = IdEncryptSerializer.class)
    @ApiModelProperty("分享链接的ID")
    private Long shareId;

    @JsonSerialize(using = IdEncryptSerializer.class)
    @ApiModelProperty("分享链接的名称")
    private String shareName;

    @JsonSerialize(using = IdEncryptSerializer.class)
    @ApiModelProperty("分享链接的URL")
    private String shareUrl;

    @JsonSerialize(using = IdEncryptSerializer.class)
    @ApiModelProperty("分享链接的分享码")
    private String shareCode;

    @JsonSerialize(using = IdEncryptSerializer.class)
    @ApiModelProperty("分享链接的状态")
    private Integer shareStatus;
}
