package com.evan.evanoj.judge;

import com.evan.evanoj.model.vo.QuestionSubmitVO;

public interface JudgeService {
    QuestionSubmitVO doJudge(long questionSubmitId);
}
