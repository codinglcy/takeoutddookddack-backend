package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckResult {
    private String result;
    private String message;

    @Builder
    public CheckResult(String result, String message){
        this.result = result;
        this.message = message;
    }
}
