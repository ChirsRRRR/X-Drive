package com.imooc.pan.server.modules.file.po;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "创建文件夹实体")
public class CreateFolderPO implements Serializable {

    private static final long serialVersionUID = -6351299201917095946L;

    @ApiModelProperty(value = "加密的父文件夹ID", required = true)
    @NotBlank(message = "父文件夹ID不能为空")
    private String parentId;

    @ApiModelProperty(value = "文件夹名称", required = true)
    @NotBlank(message = "文件夹名称不能为空")
    private String folderName;
}
