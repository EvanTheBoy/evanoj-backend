package com.evan.evanoj.model.dto.questinsubmit;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子点赞请求
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 编程代码
     */
    private String code;

    /**
     * 判题状态(0-待判题  1-判题中  2-成功  3-失败)
     */
    private Integer status;

    /**
     * 题目 id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}