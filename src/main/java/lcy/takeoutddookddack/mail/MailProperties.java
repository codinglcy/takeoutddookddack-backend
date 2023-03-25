package lcy.takeoutddookddack.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("newpwd.mail")
public class MailProperties {
    private String host;
    private int port;
    private String username;
    private String password;
}
