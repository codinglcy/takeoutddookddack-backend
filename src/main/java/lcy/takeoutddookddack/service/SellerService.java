package lcy.takeoutddookddack.service;

import com.mongodb.client.result.DeleteResult;
import io.jsonwebtoken.Claims;
import lcy.takeoutddookddack.domain.CheckResult;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.error.CustomException;
import lcy.takeoutddookddack.error.ErrorCode;
import lcy.takeoutddookddack.repository.SellerRepository;
import lcy.takeoutddookddack.jwt.JwtProvider;
import lcy.takeoutddookddack.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    @Value("${config.url}")
    private String siteUrl;
    private final SellerRepository sellerRepository;
    private final ShopRepository shopRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public Seller create(Seller seller){
        Seller newSeller = sellerRepository.saveNew(seller);
        return newSeller;
    }

    public CheckResult idCheck(String sellerId){
        Seller findSeller = sellerRepository.findBySellerId(sellerId);

        String result = "";
        String message = "";

        if (findSeller == null){
            result = "OK";
            message = "해당 아이디는 사용이 가능합니다.";
        }else{
            result = "ALREADY";
            message = "해당 아이디는 이미 사용중인 아이디입니다.";
        }

        CheckResult checkResult = CheckResult.builder().result(result).message(message).build();
        return checkResult;
    }

    public List<Seller> findAll(){
        List<Seller> allSeller = sellerRepository.findAll();
        return allSeller;
    }

    public Seller findById(String id){
        Seller seller = sellerRepository.findById(id);
        return seller;
    }

    public String editSeller(Claims currentSeller, Seller seller){
        String id = currentSeller.get("id", String.class);
        String sellerId = currentSeller.get("sellerId", String.class);

        Seller updatedSeller = sellerRepository.update(id, seller);

        if (sellerId != updatedSeller.getSellerId()){
            String accessToken = jwtProvider.createAccessToken(updatedSeller.getId(), updatedSeller.getSellerId());
            String shopId = shopRepository.findByUrl(siteUrl+"buypage/"+sellerId).getId();
            shopRepository.updateUrl(shopId, updatedSeller.getSellerId());
            return accessToken;
        }
        return "";
    }

    public String login(String sellerId, String pwd){
        Seller findSellerId = sellerRepository.findBySellerId(sellerId);

        if (findSellerId == null){
            throw new CustomException(ErrorCode.SELLERID_NOT_FOUND);
        }

        if (passwordEncoder.matches(pwd, findSellerId.getPwd())){
            String accessToken = jwtProvider.createAccessToken(findSellerId.getId(), sellerId);
            String refreshToken = jwtProvider.createRefreshToken();

            sellerRepository.updateRefreshToken(findSellerId.getId(),refreshToken);

            return accessToken;
        }else{
            throw new CustomException(ErrorCode.UNCORRECT_PASSWORD);
        }
    }

    public DeleteResult removeById(Claims currentSeller){
        String id = currentSeller.get("id", String.class);
        return sellerRepository.deleteById(id);
    }

    public String checkRefreshToken(String id){
        Seller seller = sellerRepository.findById(id);
        String refreshToken = seller.getRefreshToken();

        Date exp = jwtProvider.parseClaims(refreshToken).getExpiration();
        Date now = new Date();
        long diffDays = ((exp.getTime() - now.getTime()) / 1000) / (24*60*60);

        if (exp.before(now)){
            return "리프레시토큰 만료. 재로그인 해주세요.";
        }else {
            if (diffDays <= 14){
                String newRefreshToken = jwtProvider.createRefreshToken();
                sellerRepository.updateRefreshToken(id, newRefreshToken);
            }

            String newAccessToken = jwtProvider.createAccessToken(id, seller.getSellerId());
            return newAccessToken;
        }

    }

    public void logout(String id){
        sellerRepository.deleteRefreshToken(id);
    }

}
