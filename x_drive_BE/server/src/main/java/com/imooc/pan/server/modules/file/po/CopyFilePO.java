package com.imooc.pan.server.modules.file.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import java.io.Serializable;

@ApiModel("文件复制参数实体对象")
@Data
public class CopyFilePO implements Serializable {

    private static final long serialVersionUID = 4644212249812982541L;

    @ApiModelProperty("要复制的文件ID集合，多个使用公用分隔符隔开")
    @NotBlank(message = "请选择要复制的文件")
    private String fileIds;

    @ApiModelProperty("要复制到的目标文件夹")
    @NotBlank(message = "请选择要复制到的哪个文件夹下面")
    private String targetParentId;

}
