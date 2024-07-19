package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Question;
import com.yupi.springbootinit.service.QuestionService;
import com.yupi.springbootinit.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author EvanTheBoy
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-07-18 23:06:38
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




