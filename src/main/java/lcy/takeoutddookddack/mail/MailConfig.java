package lcy.takeoutddookddack.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {
    Properties pt = new Properties();

    private final MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailService(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(this.mailProperties.getHost());
        javaMailSender.setPort(this.mailProperties.getPort());
        javaMailSender.setUsername(this.mailProperties.getUsername());
        javaMailSender.setPassword(this.mailProperties.getPassword());

        pt.put("mail.smtp.auth", true);
        pt.put("mail.smtp.ssl.enable", true);

        javaMailSender.setJavaMailProperties(pt);
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }
}
