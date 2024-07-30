package com.evan.evanoj.judge.codesandbox;

import com.evan.evanoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.evan.evanoj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.evan.evanoj.judge.codesandbox.impl.ThirdPartyCodeSandBox;

public class CodeSandBoxFactory {
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
