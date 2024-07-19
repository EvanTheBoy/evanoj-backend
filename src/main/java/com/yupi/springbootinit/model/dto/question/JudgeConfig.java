package com.yupi.springbootinit.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 */
@Data
public class JudgeConfig {
    /**
     * 内存限制(KB)
     */
    private Long memoryLimit;

    /**
     * 堆栈限制(KB)
     */
    private Long stackLimit;

    /**
     * 时间限制(ms)
     */
    private Long timeLimit;
}
