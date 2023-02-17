package lcy.takeoutddookddack.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldControllerTest {
    @GetMapping("api/hello")
    public String test(){
        return "Hello, world!!";
    }
}
