package com.imooc.pan.server.modules.share.context;

import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 文件下载上下文实体对象
 */
@Data
public class ShareFileDownloadContext implements Serializable {

    private static final long serialVersionUID = 6163063566421910008L;

    /**
     * 要下载的文件ID
     */
    private Long fileId;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 分享ID
     */
    private Long shareId;

    /**
     * 响应实体
     */
    private HttpServletResponse response;
}
