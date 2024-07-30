package com.evan.evanoj.judge;

import com.evan.evanoj.model.entity.QuestionSubmit;
import com.evan.evanoj.model.vo.QuestionSubmitVO;
import com.evan.evanoj.service.QuestionService;
import com.evan.evanoj.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmitVO doJudge(long questionSubmitId) {
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);

        return null;
    }
}
