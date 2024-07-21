package com.evan.evanoj.model.dto.questinsubmit;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {
    /**
     * 程序执行内存(KB)
     */
    private Long memory;

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 程序执行时间(ms)
     */
    private Long time;
}
