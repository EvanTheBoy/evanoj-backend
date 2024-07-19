package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.QuestionSubmit;
import com.yupi.springbootinit.service.QuestionSubmitService;
import com.yupi.springbootinit.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;

/**
* @author EvanTheBoy
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-07-19 10:41:55
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

}




