package com.imooc.pan.server.modules.recycle;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import com.imooc.pan.core.exception.RPanBusinessException;
import com.imooc.pan.server.RPanServerLauncher;
import com.imooc.pan.server.modules.file.context.CreateFolderContext;
import com.imooc.pan.server.modules.file.context.DeleteFileContext;
import com.imooc.pan.server.modules.file.context.FileChunkMergeContext;
import com.imooc.pan.server.modules.file.context.FileChunkUploadContext;
import com.imooc.pan.server.modules.file.enums.MergeFlagEnum;
import com.imooc.pan.server.modules.file.service.IUserFileService;
import com.imooc.pan.server.modules.file.vo.FileChunkUploadVO;
import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;
import com.imooc.pan.server.modules.recycle.context.DeleteContext;
import com.imooc.pan.server.modules.recycle.context.QueryRecycleFileListContext;
import com.imooc.pan.server.modules.recycle.context.RestoreContext;
import com.imooc.pan.server.modules.recycle.service.IRecycleService;
import com.imooc.pan.server.modules.user.context.UserLoginContext;
import com.imooc.pan.server.modules.user.context.UserRegisterContext;
import com.imooc.pan.server.modules.user.service.IUserService;
import com.imooc.pan.server.modules.user.vo.UserInfoVO;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 回收站模块单元测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RPanServerLauncher.class)
@Transactional // 事务回滚，即能进行数功能测试，又不会对数据库造成影响
public class RecycleTest {

    @Autowired
    private IUserFileService iUserFileService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRecycleService iRecycleService;

    /**
     * 测试查询回收站文件列表成功
     */
    @Test
    public void testQueryRecyclesSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        //创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-old");

        Long fieldId = iUserFileService.createFolder(context);
        Assert.notNull(fieldId);

        //删除该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fieldId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        //查询回收列表，检验列表的长度为1
        QueryRecycleFileListContext queryRecycleFileListContext = new QueryRecycleFileListContext();
        queryRecycleFileListContext.setUserId(userId);
        List<RPanUserFileVO> recycles = iRecycleService.recycles(queryRecycleFileListContext);

        Assert.isTrue(CollectionUtils.isNotEmpty(recycles) && recycles.size() == 1);

    }

    /**
     * 测试文件还原成功
     */
    @Test
    public void testFileRestoreSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        //创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-old");

        Long fieldId = iUserFileService.createFolder(context);
        Assert.notNull(fieldId);

        //删除该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fieldId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        //文件还原操作
        RestoreContext restoreContext = new RestoreContext();
        restoreContext.setUserId(userId);
        restoreContext.setFileIdList(Lists.newArrayList(fieldId));
        iRecycleService.restore(restoreContext);
    }

    /**
     * 测试文件还原失败-错误的用户ID
     */
    @Test(expected = RPanBusinessException.class)
    public void testFileRestoreFailByWrongUserId() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        //创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-old");

        Long fieldId = iUserFileService.createFolder(context);
        Assert.notNull(fieldId);

        //删除该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fieldId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        //文件还原操作
        RestoreContext restoreContext = new RestoreContext();
        restoreContext.setUserId(userId + 1);
        restoreContext.setFileIdList(Lists.newArrayList(fieldId));
        iRecycleService.restore(restoreContext);
    }

    /**
     * 测试文件还原失败-错误的文件名称
     */
    @Test(expected = RPanBusinessException.class)
    public void testFileRestoreFailByWrongFilename1() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        //创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-1");

        Long fieldId = iUserFileService.createFolder(context);
        Assert.notNull(fieldId);

        //删除该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fieldId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        context.setFolderName("folder-name-1");
        fieldId = iUserFileService.createFolder(context);
        Assert.notNull(fieldId);

        //文件还原操作
        RestoreContext restoreContext = new RestoreContext();
        restoreContext.setUserId(userId);
        restoreContext.setFileIdList(Lists.newArrayList(fieldId));
        iRecycleService.restore(restoreContext);
    }

    /**
     * 测试文件还原失败-错误的文件名称
     */
    @Test(expected = RPanBusinessException.class)
    public void testFileRestoreFailByWrongFilename2() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        //创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-1");

        Long fieldId1 = iUserFileService.createFolder(context);
        Assert.notNull(fieldId1);

        //删除该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fieldId1);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        context.setFolderName("folder-name-1");
        Long fieldId2 = iUserFileService.createFolder(context);
        Assert.notNull(fieldId2);

        fileIdList.add(fieldId2);
        iUserFileService.deleteFile(deleteFileContext);

        //文件还原操作
        RestoreContext restoreContext = new RestoreContext();
        restoreContext.setUserId(userId);
        restoreContext.setFileIdList(Lists.newArrayList(fieldId1, fieldId2));
        iRecycleService.restore(restoreContext);
    }

    /**
     * 测试文件彻底删除失败-错误的用户ID
     */
    @Test(expected = RPanBusinessException.class)
    public void testFileDeleteFailByWrongUserId() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        //创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-1");

        Long fieldId = iUserFileService.createFolder(context);
        Assert.notNull(fieldId);

        //删除该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fieldId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        //文件彻底删除
        DeleteContext deleteContext = new DeleteContext();
        deleteContext.setUserId(userId + 1);
        deleteContext.setFileIdList(Lists.newArrayList(fieldId));
        iRecycleService.delete(deleteContext);
    }

    /**
     * 测试文件彻底删除成功
     */
    @Test
    public void testFileDeleteSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        //创建一个文件夹
        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name-1");

        Long fieldId = iUserFileService.createFolder(context);
        Assert.notNull(fieldId);

        //删除该文件夹
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<Long> fileIdList = Lists.newArrayList();
        fileIdList.add(fieldId);
        deleteFileContext.setFileIdList(fileIdList);
        deleteFileContext.setUserId(userId);
        iUserFileService.deleteFile(deleteFileContext);

        //文件彻底删除
        DeleteContext deleteContext = new DeleteContext();
        deleteContext.setUserId(userId);
        deleteContext.setFileIdList(Lists.newArrayList(fieldId));
        iRecycleService.delete(deleteContext);
    }



    /**************************************private method**************************************/

    /**
     * 用户注册
     *
     * @return 新用户的id
     */
    private Long register() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);
        return register;
    }

    /**
     * 查询用户的基本信息
     * @param userId
     */
    private UserInfoVO info(Long userId) {
        UserInfoVO userInfoVO = iUserService.info(userId);
        Assert.notNull(userInfoVO);
        return userInfoVO;
    }


    private final static String USERNAME = "IUUUUU";
    private final static String PASSWORD = "123456";
    private final static String QUESTION = "question";
    private final static String ANSWER = "answer";

    /**
     * 构建注册用户上下文实体
     */
    private UserRegisterContext createUserRegisterContext() {
        UserRegisterContext context = new UserRegisterContext();
        context.setUsername(USERNAME);
        context.setPassword(PASSWORD);
        context.setQuestion(QUESTION);
        context.setAnswer(ANSWER);

        return context;
    }

}
