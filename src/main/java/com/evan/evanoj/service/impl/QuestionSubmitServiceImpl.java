package com.evan.evanoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evan.evanoj.common.ErrorCode;
import com.evan.evanoj.exception.BusinessException;
import com.evan.evanoj.model.dto.questinsubmit.QuestionSubmitAddRequest;
import com.evan.evanoj.model.dto.questinsubmit.QuestionSubmitQueryRequest;
import com.evan.evanoj.model.entity.Question;
import com.evan.evanoj.model.entity.QuestionSubmit;
import com.evan.evanoj.model.entity.User;
import com.evan.evanoj.model.enums.QuestionSubmitLanguageEnum;
import com.evan.evanoj.model.enums.QuestionSubmitStatusEnum;
import com.evan.evanoj.model.vo.QuestionSubmitVO;
import com.evan.evanoj.service.QuestionService;
import com.evan.evanoj.service.QuestionSubmitService;
import com.evan.evanoj.mapper.QuestionSubmitMapper;
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
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 查看编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 创建 questionSubmit 实例
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserid(loginUser.getId());
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        // 传入相应枚举类, 统一为等待判题
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean saved = this.save(questionSubmit);
        if (!saved) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入失败");
        }
        return questionSubmit.getId();
    }

    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        return null;
    }

    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        return null;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        return null;
    }
}
