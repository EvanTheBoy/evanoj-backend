package com.evan.evanoj.judge;

import cn.hutool.json.JSONUtil;
import com.evan.evanoj.common.ErrorCode;
import com.evan.evanoj.exception.BusinessException;
import com.evan.evanoj.judge.codesandbox.CodeSandBox;
import com.evan.evanoj.judge.codesandbox.CodeSandBoxFactory;
import com.evan.evanoj.judge.codesandbox.CodeSandBoxProxy;
import com.evan.evanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.evan.evanoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.evan.evanoj.model.dto.questinsubmit.JudgeInfo;
import com.evan.evanoj.model.dto.question.JudgeCase;
import com.evan.evanoj.model.dto.question.JudgeConfig;
import com.evan.evanoj.model.entity.Question;
import com.evan.evanoj.model.entity.QuestionSubmit;
import com.evan.evanoj.model.enums.JudgeInfoMessageEnum;
import com.evan.evanoj.model.enums.QuestionSubmitStatusEnum;
import com.evan.evanoj.model.vo.QuestionSubmitVO;
import com.evan.evanoj.service.QuestionService;
import com.evan.evanoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmitVO doJudge(long questionSubmitId) {
        // 获取题目
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "找不到题目提交记录");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "找不到题目记录");
        }
        // 更新题目判题状态
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        QuestionSubmit questionSubmitVoUpdate = new QuestionSubmit();
        questionSubmitVoUpdate.setId(questionSubmit.getId());
        questionSubmitVoUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean updated = questionSubmitService.updateById(questionSubmitVoUpdate);
        if (!updated) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }
        // 调用沙箱
        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .inputList(inputList)
                .code(questionSubmit.getCode())
                .language(questionSubmit.getLanguage())
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
        // 根据代码沙箱的判题结果, 设置判题的状态和其他信息
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;
        if (inputList.size() != outputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            return null;
        }
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase aCase = judgeCaseList.get(i);
            if (!aCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                return null;
            }
        }
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        String questionJudgeConfig = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(questionJudgeConfig, JudgeConfig.class);
        Long expectedMemoryLimit = judgeConfig.getMemoryLimit();
        Long expectedTimeLimit = judgeConfig.getTimeLimit();
        if (memory > expectedMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_OVERFLOW;
            return null;
        }
        if (time > expectedTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            return null;
        }
        return null;
    }
}
