package com.evan.evanoj.judge;

import com.evan.evanoj.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit doJudge(long questionSubmitId);
}
