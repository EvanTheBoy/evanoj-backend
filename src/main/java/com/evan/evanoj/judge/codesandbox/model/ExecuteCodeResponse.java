package com.evan.evanoj.judge.codesandbox.model;

import com.evan.evanoj.model.dto.questinsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {
    /**
     * 输出列表
     */
    private List<String> outputList;

    /**
     * 程序执行信息
     */
    private Integer message;

    /**
     * 程序执行状态
     */
    private String status;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
