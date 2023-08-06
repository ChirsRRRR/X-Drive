//package com.imooc.pan.server.common.event.file;
//
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.event.ApplicationContextEvent;
//
//import java.util.List;
//import java.util.Objects;
//
///**
// * 文件删除事件
// */
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
//public class DeleteFileEvent extends ApplicationEvent {
//
//    private List<Long> fileIdList;
//
//    public DeleteFileEvent(Object source, List<Long> fileIdList) {
//        super(source);
//        this.fileIdList = fileIdList;
//    }
//}
