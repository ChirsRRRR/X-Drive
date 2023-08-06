//package com.imooc.pan.server.common.listener.search;
//
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.imooc.pan.core.utils.IdUtil;
//import com.imooc.pan.server.common.event.search.UserSearchEvent;
//import com.imooc.pan.server.modules.user.entity.RPanUserSearchHistory;
//import com.imooc.pan.server.modules.user.service.IUserSearchHistoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * 用户搜索时间监听器
// */
//@Component
//public class UserSearchEventListener {
//
//    @Autowired
//    private IUserSearchHistoryService iUserSearchHistoryService;
//
//    @EventListener(classes = UserSearchEvent.class)
//    @Async(value = "eventListenerTaskExecutor")
//    public void saveSearchHistory(UserSearchEvent event) {
//        RPanUserSearchHistory record = new RPanUserSearchHistory();
//
//        record.setId(IdUtil.get());
//        record.setUseId(event.getUserId());
//        record.setSearchContent(event.getKeyword());
//        record.setCreateTime(new Date());
//        record.setUpdateTime(new Date());
//
//        try {
//            iUserSearchHistoryService.save(record);
//        } catch (DuplicateKeyException e) {
//            UpdateWrapper updateWrapper = Wrappers.update();
//            updateWrapper.eq("user_id", event.getUserId());
//            updateWrapper.eq("search_content", event.getKeyword());
//            updateWrapper.set("update_time", new Date());
//            iUserSearchHistoryService.update(updateWrapper);
//        }
//    }
//}
