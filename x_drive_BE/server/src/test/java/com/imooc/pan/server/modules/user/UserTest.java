package com.imooc.pan.server.modules.user;

import cn.hutool.core.lang.Assert;
import com.imooc.pan.core.exception.RPanBusinessException;
import com.imooc.pan.core.utils.JwtUtil;
import com.imooc.pan.server.RPanServerLauncher;
import com.imooc.pan.server.modules.user.contants.UserConstants;
import com.imooc.pan.server.modules.user.context.*;
import com.imooc.pan.server.modules.user.service.IUserService;
import com.imooc.pan.server.modules.user.vo.UserInfoVO;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户模块单元测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RPanServerLauncher.class)
@Transactional // 事务回滚，即能进行数功能测试，又不会对数据库造成影响
public class UserTest {

    @Autowired
    private IUserService iuserService;

    /**
     * 测试成功注册用户信息
     */
    @Test
    public void testRegister() {
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);
    }

    /**
     * 测试重复用户名称注册幂等
     */
    @Test(expected = RPanBusinessException.class)
    public void testRegisterDuplicatedUsername() {

        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);
        iuserService.register(context);

    }

    /**
     * 测试登陆成功
     */
    @Test
    public void loginSuccess() {
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = iuserService.login(userLoginContext);

        Assert.isTrue(StringUtils.isNotBlank(accessToken));
    }

    /**
     * 测试登录失败：用户名不存在的情况
     */
    @Test(expected = RPanBusinessException.class)
    public void wrongUsername() {
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setUsername(userLoginContext.getUsername() + "_changed");
        iuserService.login(userLoginContext);
    }

    /**
     * 测试登录失败：密码错误的情况
     */
    @Test(expected = RPanBusinessException.class)
    public void wrongPassword() {
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setPassword(userLoginContext.getPassword() + "_changed");
        iuserService.login(userLoginContext);
    }

    /**
     * 用户成功退出登录
     */
    @Test
    public void exitSuccess() {
        //先有登录成功的校验
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = iuserService.login(userLoginContext);

        Assert.isTrue(StringUtils.isNotBlank(accessToken));

        //然后测试退出登录是否成功
        Long userId = (Long) JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);
        iuserService.exit(userId);
    }

    /**
     * 校验用户名通过
     */
    @Test
    public void checkUsernameSuccess() {
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        CheckUsernameContext checkUsernameContext = new CheckUsernameContext();
        checkUsernameContext.setUsername(USERNAME);
        String question = iuserService.checkUsername(checkUsernameContext);
        Assert.isTrue(StringUtils.isNotBlank(question));
    }

    /**
     * 校验用户名失败-没有查询到该用户
     */
    @Test(expected = RPanBusinessException.class)
    public void checkUsernameNotExists() {
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        CheckUsernameContext checkUsernameContext = new CheckUsernameContext();
        checkUsernameContext.setUsername(USERNAME + "_changed");
        iuserService.checkUsername(checkUsernameContext);
    }

    /**
     * 校验密保问题答案通过
     */
    @Test
    public void checkAnswerSuccess() {
        UserRegisterContext context = createUserRegisterContext();

        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER);

        String token = iuserService.checkAnswer(checkAnswerContext);

        Assert.isTrue(StringUtils.isNotBlank(token));
    }

    /**
     * 校验密保问题答案失败
     */
    @Test(expected = RPanBusinessException.class)
    public void checkAnswerFail() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER + "_changed");

        iuserService.checkAnswer(checkAnswerContext);
    }

    /**
     * 成功重置密码
     */
    @Test
    public void resetPasswordSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER);

        String token = iuserService.checkAnswer(checkAnswerContext);

        Assert.isTrue(StringUtils.isNotBlank(token));

        ResetPasswordContext resetPasswordContext = new ResetPasswordContext();
        resetPasswordContext.setUsername(USERNAME);
        resetPasswordContext.setPassword(PASSWORD + "_changed");
        resetPasswordContext.setToken(token);

        iuserService.resetPassword(resetPasswordContext);
    }

    /**
     * 用户重置密码失败-token异常
     *
     */
    @Test(expected = RPanBusinessException.class)
    public void resetPasswordTokenError() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER);

        String token = iuserService.checkAnswer(checkAnswerContext);

        Assert.isTrue(StringUtils.isNotBlank(token));

        ResetPasswordContext resetPasswordContext = new ResetPasswordContext();
        resetPasswordContext.setUsername(USERNAME);
        resetPasswordContext.setPassword(PASSWORD + "_changed");
        resetPasswordContext.setToken(token + "_changed");

        iuserService.resetPassword(resetPasswordContext);
    }

    /**
     * 正常在线修改密码
     */
    @Test
    public void changePasswordSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        ChangePasswordContext changePasswordContext = new ChangePasswordContext();

        changePasswordContext.setUserId(register);
        changePasswordContext.setOldPassword(PASSWORD);
        changePasswordContext.setNewPassword(PASSWORD + "_changed");

        iuserService.changePassword(changePasswordContext);
    }

    /**
     * 修改密码失败-原密码错误
     */
    @Test(expected = RPanBusinessException.class)
    public void changePasswordFailByWrongOldPassword() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        ChangePasswordContext changePasswordContext = new ChangePasswordContext();

        changePasswordContext.setUserId(register);
        changePasswordContext.setOldPassword(PASSWORD + "_changed");
        changePasswordContext.setNewPassword(PASSWORD + "_changed");

        iuserService.changePassword(changePasswordContext);
    }

    @Test
    public void testQueryUserInfo() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iuserService.register(context);
        Assert.isTrue(register.longValue() > 0L);

        UserInfoVO userInfoVO = iuserService.info(register);
        Assert.notNull(userInfoVO);
    }

    /**************************************private method**************************************/

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

    /**
     * 构建用户登录上下文实体
     * @return
     */
    private UserLoginContext createUserLoginContext() {
        UserLoginContext userLoginContext = new UserLoginContext();
        userLoginContext.setUsername(USERNAME);
        userLoginContext.setPassword(PASSWORD);
        return userLoginContext;
    }



}
