package com.evan.evanoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evan.evanoj.common.ErrorCode;
import com.evan.evanoj.exception.BusinessException;
import com.evan.evanoj.model.dto.questinsubmit.QuestionSubmitAddRequest;
import com.evan.evanoj.model.entity.Question;
import com.evan.evanoj.model.entity.QuestionSubmit;
import com.evan.evanoj.model.entity.User;
import com.evan.evanoj.service.QuestionService;
import com.evan.evanoj.service.QuestionSubmitService;
import com.evan.evanoj.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author EvanTheBoy
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-07-19 10:41:55
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    @Override
    public int doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 创建 questionSubmit 实例
        QuestionSubmit questionSubmit = new QuestionSubmit();
        
        QuestionSubmitService questionSubmitService = (QuestionSubmitService) AopContext.currentProxy();
    }
}
