package com.evan.evanoj.service;

import com.evan.evanoj.model.dto.questinsubmit.QuestionSubmitAddRequest;
import com.evan.evanoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.evan.evanoj.model.entity.User;

/**
* @author EvanTheBoy
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-07-19 10:41:55
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
}
