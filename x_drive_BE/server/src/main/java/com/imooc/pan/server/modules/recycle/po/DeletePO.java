package com.imooc.pan.server.modules.recycle.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("文件删除参数实体")
@Data
public class DeletePO implements Serializable {

    private static final long serialVersionUID = -7203979525739507591L;

    @ApiModelProperty(value = "要删除的文件ID集合，多个则使用公用分隔符分隔", required = true)
    @NotBlank
    private String fileIds;
}
