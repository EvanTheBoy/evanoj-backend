package com.yupi.springbootinit.model.dto.questinsubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * 帖子点赞请求
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 帖子 id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}