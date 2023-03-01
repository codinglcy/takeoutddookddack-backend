package lcy.takeoutddookddack.service;

import com.mongodb.client.result.DeleteResult;
import io.jsonwebtoken.Claims;
import lcy.takeoutddookddack.domain.CheckResult;
import lcy.takeoutddookddack.domain.LoginResponse;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.repository.SellerRepository;
import lcy.takeoutddookddack.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
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

    public Seller editSeller(String id, Seller seller){
        Seller updatedSeller = sellerRepository.update(id, seller);
        return updatedSeller;
    }

    public LoginResponse login(String sellerId, String pwd){
        Seller findSellerId = sellerRepository.findBySellerId(sellerId);

        if (findSellerId == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 아이디입니다.");
        }

        if (passwordEncoder.matches(pwd, findSellerId.getPwd())){
            return LoginResponse.builder()
                    .id(findSellerId.getId())
                    .accessToken(jwtProvider.createAccessToken(findSellerId.getId(), sellerId))
                    .refreshToken(jwtProvider.createRefreshToken(findSellerId.getId(), sellerId))
                    .build();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "비밀번호가 일치하지 않습니다");
        }
    }

    public DeleteResult removeById(String id){
        return sellerRepository.deleteById(id);
    }

}
