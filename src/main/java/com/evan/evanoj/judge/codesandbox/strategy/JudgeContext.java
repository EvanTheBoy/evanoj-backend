package com.evan.evanoj.judge.codesandbox.strategy;

import com.evan.evanoj.model.dto.questinsubmit.JudgeInfo;
import com.evan.evanoj.model.dto.question.JudgeCase;
import com.evan.evanoj.model.entity.Question;
import com.evan.evanoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

@Data
public class JudgeContext {
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 输入列表
     */
    private List<String> inputList;

    /**
     * 输出列表
     */
    private List<String> outputList;

    /**
     * 判题 case 列表
     */
    private List<JudgeCase> judgeCaseList;

    /**
     * 题目
     */
    private Question question;

    /**
     * 题目提交
     */
    private QuestionSubmit questionSubmit;
}
