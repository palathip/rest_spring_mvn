package com.rest_spring_mvn.directdebit;


import com.rest_spring_mvn.model.baybillingdetail.BillingDetail;
import org.springframework.stereotype.Service;

@Service
public class BillingDetailMapperValue {

    public BillingDetail mapperReader(String text){
        BillingDetail result = new BillingDetail();
        result.setBayMerchantNo(text.substring(0,9));
        result.setStatementPref(text.substring(9,34).replace(" ",""));
        result.setCardNo(text.substring(34,50));
        result.setExpiryDate(text.substring(50,54));
        result.setAmount(Long.valueOf(text.substring(54,65)));
        result.setApplicationNo(text.substring(65,85).replace(" ",""));
        result.setPeriod(Long.valueOf(text.substring(85,91)));
        result.setPolicyYear(text.substring(91,93));
        result.setDescription(text.substring(93,151).replace(" ",""));
        result.setDiget(text.substring(151,163));
        return result;

    }
}
