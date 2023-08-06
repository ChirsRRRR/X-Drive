package com.imooc.pan.server.modules.recycle.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("文件还原参数实体")
@Data
public class RestorePO implements Serializable {

    private static final long serialVersionUID = 1698650542739591077L;

    @ApiModelProperty(value = "要还原的文件ID集合，多个ID则使用公用分隔符进行分隔", required = true)
    @NotBlank(message = "请选择要删除的文件")
    private String fileIds;
}
