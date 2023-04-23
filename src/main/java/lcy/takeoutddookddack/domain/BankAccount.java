package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Getter
@Document
public class BankAccount {
    private String bank;
    private String accountNum;
    private String name;

    @Builder
    public BankAccount(String bank, String accountNum, String name){
        this.bank = bank;
        this.accountNum = accountNum;
        this.name = name;
    }
}
