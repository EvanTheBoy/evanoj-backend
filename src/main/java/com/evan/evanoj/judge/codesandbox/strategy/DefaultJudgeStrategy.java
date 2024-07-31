package com.evan.evanoj.judge.codesandbox.strategy;

import cn.hutool.json.JSONUtil;
import com.evan.evanoj.model.dto.questinsubmit.JudgeInfo;
import com.evan.evanoj.model.dto.question.JudgeCase;
import com.evan.evanoj.model.dto.question.JudgeConfig;
import com.evan.evanoj.model.entity.Question;
import com.evan.evanoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(judgeInfo.getTime());
        judgeInfoResponse.setMemory(judgeInfo.getMemory());

        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        if (inputList.size() != outputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase aCase = judgeCaseList.get(i);
            if (!aCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        // 判断题目限制
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        String questionJudgeConfig = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(questionJudgeConfig, JudgeConfig.class);
        Long expectedMemoryLimit = judgeConfig.getMemoryLimit();
        Long expectedTimeLimit = judgeConfig.getTimeLimit();
        if (memory > expectedMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_OVERFLOW;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if (time > expectedTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
