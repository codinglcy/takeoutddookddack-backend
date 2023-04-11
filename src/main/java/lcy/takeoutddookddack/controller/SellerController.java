package lcy.takeoutddookddack.controller;

import com.mongodb.client.result.DeleteResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import lcy.takeoutddookddack.domain.CheckResult;
import lcy.takeoutddookddack.domain.LoginResult;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.error.CustomException;
import lcy.takeoutddookddack.error.ErrorCode;
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
        return Seller.builder()
                .id(createdSeller.getId())
                .sellerId(createdSeller.getSellerId())
                .name(createdSeller.getName())
                .email(createdSeller.getEmail())
                .shopPage(createdSeller.getShopPage())
                .build();
    }

    @GetMapping("/all")
    public List<Seller> getAllSeller(){
        List<Seller> sellerList = sellerService.findAll();
        return sellerList;
    }

    @GetMapping("")
    public Seller getSeller(){
        Claims currentSeller = securityUtil.getCurrentSeller();
        String id = currentSeller.get("id", String.class);

        Seller seller = sellerService.findById(id);
        return Seller.builder()
                .id(seller.getId())
                .sellerId(seller.getSellerId())
                .email(seller.getEmail())
                .name(seller.getName())
                .shopPage(seller.getShopPage())
                .build();
    }

    @PatchMapping("/checkpwdmail")
    public String checkpwdmail(@RequestBody Map<String, String> body){
        String sellerId = body.get("sellerId");
        String name = body.get("name");
        String email = body.get("email");

        return sellerService.checkAndSendPwdmail(sellerId, name, email);
    }

    @PatchMapping("/pwdmail")
    public String pwdmail(@RequestBody Map<String, String> body){
        String sellerId = body.get("sellerId");
        String email = body.get("email");

        sellerService.sendMail(sellerId, email);
        return "이메일 "+email+"로 임시 비밀번호를 발송했습니다.";
    }

    @PatchMapping("")
    public String editSeller(@RequestBody @Valid Seller sellerInfo){
        Seller updateSeller;

        if (sellerInfo.getPwd() == null){
            updateSeller = Seller.builder()
                    .sellerId(sellerInfo.getSellerId())
                    .name(sellerInfo.getName())
                    .email(sellerInfo.getEmail())
                    .shopPage(siteUrl+"buypage/"+sellerInfo.getSellerId())
                    .build();
        }else{
            updateSeller = Seller.builder()
                    .sellerId(sellerInfo.getSellerId())
                    .pwd(passwordEncoder.encode(sellerInfo.getPwd()))
                    .name(sellerInfo.getName())
                    .email(sellerInfo.getEmail())
                    .shopPage(siteUrl+"buypage/"+sellerInfo.getSellerId())
                    .build();
        }

        Claims currentSeller = securityUtil.getCurrentSeller();

        String updateToken = sellerService.editSeller(currentSeller, updateSeller);
        return updateToken;
    }

    @PostMapping("/login")
    public LoginResult login(@RequestBody Map<String, String> loginInfo){
        String loginSellerId = loginInfo.get("sellerId");
        String loginPassword = loginInfo.get("pwd");

        LoginResult loginResponse = sellerService.login(loginSellerId, loginPassword);

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

    @GetMapping("/checkAccessToken")
    public String checkToken(){
        try{
            if (!(boolean)securityUtil.getCurrentSeller().get("expBeforeNow")){
                return "로그인 상태입니다.";
            }
            return null;
        }catch (JwtException e){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

    }

    @GetMapping("/checkRefreshToken")
    public LoginResult checkRefreshToken(@RequestParam("token") String refreshToken){
        return sellerService.checkRefreshToken(refreshToken);
    }


}
