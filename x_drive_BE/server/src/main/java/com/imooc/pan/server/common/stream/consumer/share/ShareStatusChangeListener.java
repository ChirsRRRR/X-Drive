package com.imooc.pan.server.common.stream.consumer.share;

import com.imooc.pan.server.common.stream.event.file.DeleteFileEvent;
import com.imooc.pan.server.common.stream.event.file.FileRestoreEvent;
import com.imooc.pan.server.common.stream.channel.PanChannels;
import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import com.imooc.pan.server.modules.file.enums.DelFlagEnum;
import com.imooc.pan.server.modules.file.service.IUserFileService;
import com.imooc.pan.server.modules.share.service.IShareService;
import com.imooc.pan.stream.core.AbstractConsumer;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 监听文件状态变更导致分享状态变更的处理器
 */
public class ShareStatusChangeListener extends AbstractConsumer {

    @Autowired
    private IUserFileService iUserFileService;

    @Autowired
    private IShareService iShareService;

    /**
     * 监听文件被删除之后，刷新所有受影响的分享状态
     * @param message
     */
    @StreamListener(PanChannels.DELETE_FILE_INPUT)
    @Async(value = "eventListenerTaskExecutor")
    public void changeShare2FileDeleted(Message<DeleteFileEvent> message) {
        if (isEmptyMessage(message)) {
            return;
        }
        DeleteFileEvent event = message.getPayload();
        List<Long> fileIdList = event.getFileIdList();
        if (CollectionUtils.isEmpty(fileIdList)) {
            return;
        }
        List<RPanUserFile> allRecords = iUserFileService.findAllFileRecordsByFileIdList(fileIdList);
        List<Long> allAvailableFileIdList = allRecords.stream()
                .filter(record -> Objects.equals(record.getDelFlag(), DelFlagEnum.NO.getCode()))
                .map(RPanUserFile::getFileId)
                .collect(Collectors.toList());
        allAvailableFileIdList.addAll(fileIdList);
        iShareService.refreshShareStatus(allAvailableFileIdList);
    }

    /**
     * 监听文件被还原后，刷新所有受影响的分享的状态
     * @param message
     */
    @StreamListener(PanChannels.FILE_RESTORE_INPUT)
    @Async(value = "eventListenerTaskExecutor")
    public void changeShare2Normal(Message<FileRestoreEvent> message) {
        if (isEmptyMessage(message)) {
            return;
        }
        FileRestoreEvent event = message.getPayload();
        List<Long> fileIdList = event.getFileIdList();
        if (CollectionUtils.isEmpty(fileIdList)) {
            return;
        }
        List<RPanUserFile> allRecords = iUserFileService.findAllFileRecordsByFileIdList(fileIdList);
        List<Long> allAvailableFileIdList = allRecords.stream()
                .filter(record -> Objects.equals(record.getDelFlag(), DelFlagEnum.NO.getCode()))
                .map(RPanUserFile::getFileId)
                .collect(Collectors.toList());
        allAvailableFileIdList.addAll(fileIdList);
        iShareService.refreshShareStatus(allAvailableFileIdList);
    }
}
