package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@NoArgsConstructor
public class Location {
    private String address;
    private String more;

    @Builder
    public Location(String address, String more){
        this.address = address;
        this.more = more;
    }
}
