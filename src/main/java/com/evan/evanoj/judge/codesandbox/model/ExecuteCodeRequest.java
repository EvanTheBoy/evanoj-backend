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
public class ExecuteCodeRequest {
    /**
     * 输入列表
     */
    private List<String> inputList;

    /**
     * 程序代码
     */
    private String code;

    /**
     * 代码语言
     */
    private String language;
}
