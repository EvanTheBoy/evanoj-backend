package com.yupi.springbootinit.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yupi.springbootinit.model.dto.question.JudgeConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目封装类
 */
@TableName(value ="question")
@Data
public class QuestionVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表(json 数组)
     */
    private List<String> tags;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置(json 对象)
     */
    private JudgeConfig judgeConfig;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}