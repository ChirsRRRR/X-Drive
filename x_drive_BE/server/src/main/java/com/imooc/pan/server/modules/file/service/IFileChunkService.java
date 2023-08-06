package com.imooc.pan.server.modules.file.service;

import com.imooc.pan.server.modules.file.context.FileChunkSaveContext;
import com.imooc.pan.server.modules.file.entity.RPanFileChunk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 12195
* @description 针对表【r_pan_file_chunk(文件分片信息表)】的数据库操作Service
* @createDate 2023-06-06 23:19:24
*/
public interface IFileChunkService extends IService<RPanFileChunk> {

    /**
     * 文件分片保存
     * @param context
     */
    void saveChunkFile(FileChunkSaveContext context);
}
