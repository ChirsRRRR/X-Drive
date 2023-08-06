package com.imooc.pan.server.modules.user.mapper;

import com.imooc.pan.server.modules.user.context.QueryUserSearchHistoryContext;
import com.imooc.pan.server.modules.user.entity.RPanUserSearchHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pan.server.modules.user.vo.UserSearchHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 12195
* @description 针对表【r_pan_user_search_history(用户搜索历史表)】的数据库操作Mapper
* @createDate 2023-06-06 23:16:52
* @Entity com.imooc.pan.server.modules.user.entity.RPanUserSearchHistory
*/
public interface RPanUserSearchHistoryMapper extends BaseMapper<RPanUserSearchHistory> {

    /**
     * 查询用户的最近10条历史搜索记录
     * @param context
     * @return
     */
    List<UserSearchHistoryVO> selectUserSearchHistories(@Param("param") QueryUserSearchHistoryContext context);
}




