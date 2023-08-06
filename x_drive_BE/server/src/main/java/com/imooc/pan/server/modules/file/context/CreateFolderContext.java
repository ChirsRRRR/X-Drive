package com.imooc.pan.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建文件夹上下文实体对象
 */
@Data
public class CreateFolderContext implements Serializable {
    private static final long serialVersionUID = -861882709652125971L;

    /**
     * 父文件夹id
     */
    private Long parentId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 文件夹名
     */
    private String folderName;
}
