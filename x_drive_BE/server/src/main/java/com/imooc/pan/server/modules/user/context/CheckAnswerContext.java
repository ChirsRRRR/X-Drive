package com.imooc.pan.server.modules.user.context;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class CheckAnswerContext implements Serializable {


    private static final long serialVersionUID = -2789803913084370168L;
    /**
     * 用户名称
     */
    private String username;

    /**
     * 密保问题
     */
    private String question;

    /**
     * 密保答案
     */
    private String answer;
}
