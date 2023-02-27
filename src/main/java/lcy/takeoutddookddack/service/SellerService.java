package lcy.takeoutddookddack.service;

import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.CheckResult;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

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

    public Seller editSeller(ObjectId id, Seller seller){
        Seller updatedSeller = sellerRepository.update(id, seller);
        return updatedSeller;
    }

    public DeleteResult removeById(ObjectId id){
        return sellerRepository.deleteById(id);
    }

}
