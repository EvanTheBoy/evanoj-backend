package com.evan.evanoj.judge.codesandbox;

import com.evan.evanoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.evan.evanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.evan.evanoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CodeSandBoxTest {


    @Test
    public void testCodeSandBox() {
        CodeSandBox codeSandBox = new ExampleCodeSandBox();
        String code = "int main(){}";
        List<String> inputList = Arrays.asList("1 2", "3 4");
        String language = "java";
        ExecuteCodeRequest request = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(request);
        Assertions.assertNotNull(executeCodeResponse);
    }
}
