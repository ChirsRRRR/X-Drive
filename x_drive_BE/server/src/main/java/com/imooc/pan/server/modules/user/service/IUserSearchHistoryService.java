package com.imooc.pan.server.modules.user.service;

import com.imooc.pan.server.modules.user.context.QueryUserSearchHistoryContext;
import com.imooc.pan.server.modules.user.entity.RPanUserSearchHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.user.vo.UserSearchHistoryVO;

import java.util.List;

/**
* @author 12195
* @description 针对表【r_pan_user_search_history(用户搜索历史表)】的数据库操作Service
* @createDate 2023-06-06 23:16:52
*/
public interface IUserSearchHistoryService extends IService<RPanUserSearchHistory> {

    /**
     * 查询用户的搜索记录，默认10条
     * @param context
     * @return
     */
    List<UserSearchHistoryVO> getUserSearchHistories(QueryUserSearchHistoryContext context);
}
