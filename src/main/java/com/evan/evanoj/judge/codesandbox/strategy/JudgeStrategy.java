package com.evan.evanoj.judge.codesandbox.strategy;

import com.evan.evanoj.model.dto.questinsubmit.JudgeInfo;

public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
