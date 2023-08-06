package com.imooc.pan.storage.engine.core.context;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeleteFileContext implements Serializable {


    private static final long serialVersionUID = 4868571446700431311L;

    /**
     * 要删除的物理文件路径的集合
     */
    private List<String> realFilePathList;
}
