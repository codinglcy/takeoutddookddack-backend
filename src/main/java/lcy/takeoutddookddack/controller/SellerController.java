package lcy.takeoutddookddack.controller;

import com.mongodb.client.result.DeleteResult;
import jakarta.validation.Valid;
import lcy.takeoutddookddack.domain.CheckResult;
import lcy.takeoutddookddack.domain.LoginResponse;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class SellerController {
    @Value("${config.url}")
    private String siteUrl;

    private final SellerService sellerService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/idCheck")
    public CheckResult idCheck(@RequestBody Map<String,String> body){
        CheckResult getResult = sellerService.idCheck(body.get("sellerId"));

        return getResult;
    }

    @PostMapping("")
    public Seller createSeller(@RequestBody @Valid Seller sellerInfo){
        Seller newSeller = Seller.builder()
                .sellerId(sellerInfo.getSellerId())
                .pwd(passwordEncoder.encode(sellerInfo.getPwd()))
                .name(sellerInfo.getName())
                .email(sellerInfo.getEmail())
                .shopPage(siteUrl+"buypage/"+sellerInfo.getSellerId())
                .build();
        Seller createdSeller = sellerService.create(newSeller);
        return createdSeller;
    }

    @GetMapping("/all")
    public List<Seller> getAllSeller(){
        List<Seller> sellerList = sellerService.findAll();
        return sellerList;
    }

    @PatchMapping("/{token}")
    public String editSeller(@PathVariable("token") String token, @RequestBody @Valid Seller sellerInfo){
        Seller updateSeller = Seller.builder()
                .sellerId(sellerInfo.getSellerId())
                .pwd(passwordEncoder.encode(sellerInfo.getPwd()))
                .name(sellerInfo.getName())
                .email(sellerInfo.getEmail())
                .shopPage(siteUrl+"buypage/"+sellerInfo.getSellerId())
                .build();

        String updateToken = sellerService.editSeller(token, updateSeller);
        return updateToken;
    }

    @GetMapping("/login")
    public LoginResponse login(@RequestBody Map<String, String> loginInfo){
        String loginSellerId = loginInfo.get("sellerId");
        String loginPassword = loginInfo.get("pwd");

        LoginResponse loginResponse = sellerService.login(loginSellerId, loginPassword);

        return loginResponse;
    }

    @DeleteMapping("/{token}")
    public DeleteResult removeById(@PathVariable("token") String token){
        return sellerService.removeById(token);
    }

    @GetMapping("/{token}")
    public void checkToken(@PathVariable("token") String token){
        sellerService.checkToken(token);
    }
}
