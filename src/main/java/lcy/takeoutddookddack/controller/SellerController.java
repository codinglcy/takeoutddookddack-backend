package lcy.takeoutddookddack.controller;

import com.mongodb.client.result.DeleteResult;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lcy.takeoutddookddack.domain.CheckResult;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.jwt.SecurityUtil;
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
    private final SecurityUtil securityUtil;

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

    @PatchMapping("/")
    public String editSeller(@RequestBody @Valid Seller sellerInfo){
        Seller updateSeller = Seller.builder()
                .sellerId(sellerInfo.getSellerId())
                .pwd(passwordEncoder.encode(sellerInfo.getPwd()))
                .name(sellerInfo.getName())
                .email(sellerInfo.getEmail())
                .shopPage(siteUrl+"buypage/"+sellerInfo.getSellerId())
                .build();
        Claims currentSeller = securityUtil.getCurrentSeller();

        String updateToken = sellerService.editSeller(currentSeller, updateSeller);
        return updateToken;
    }

    @GetMapping("/login")
    public String login(@RequestBody Map<String, String> loginInfo){
        String loginSellerId = loginInfo.get("sellerId");
        String loginPassword = loginInfo.get("pwd");

        String loginResponse = sellerService.login(loginSellerId, loginPassword);

        return loginResponse;
    }

    @DeleteMapping("/logout")
    public void logout(){
        Claims currentSeller = securityUtil.getCurrentSeller();
        sellerService.logout(currentSeller.getId());
    }

    @DeleteMapping("")
    public DeleteResult removeById(){
        Claims currentSeller = securityUtil.getCurrentSeller();
        return sellerService.removeById(currentSeller);
    }

    @GetMapping("/checkToken")
    public String checkToken(){
        Claims currentSeller = securityUtil.getCurrentSeller();
        if (currentSeller != null){
            return "로그인 상태입니다.";
        }
        String result = sellerService.checkRefreshToken(currentSeller.getId());
        return result;
    }

}
