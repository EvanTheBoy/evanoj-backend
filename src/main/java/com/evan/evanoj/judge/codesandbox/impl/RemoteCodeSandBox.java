package com.evan.evanoj.judge.codesandbox.impl;

import com.evan.evanoj.judge.codesandbox.CodeSandBox;
import com.evan.evanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.evan.evanoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程调用代码沙箱");
        return null;
    }
}
