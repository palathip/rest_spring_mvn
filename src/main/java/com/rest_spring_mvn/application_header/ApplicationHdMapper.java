package com.rest_spring_mvn.application_header;

import com.rest_spring_mvn.mapper.TextFileReader;
import com.rest_spring_mvn.util.Strings;
import com.rest_spring_mvn.entity.ApplicationHd;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHdMapper implements TextFileReader<ApplicationHd> {
    @Override
    public boolean preFilter(String s) {
        return !s.isEmpty();
    }

    @Override
    public ApplicationHd map(String s) {
        String[] args = s.split("\\|");
        System.out.println(args);
        if (args.length != 50 || args[0].length() != 15) return null;
        ApplicationHd a = new ApplicationHd();
        a.setBatchCode(args[0]);
        a.setBatchDate(Strings.toDate(args[1]));
        a.setApplicationNo(args[2]);
        a.setPolicyClass(args[3]);
        a.setSumInsured(Strings.toDouble(args[4]) / 100);
        a.setNetPremium(Strings.toDouble(args[5]) / 100);
        a.setGrossPremium(Strings.toDouble(args[6]) / 100);
        a.setEffectiveDate(Strings.toDate(args[7]));
        a.setExpiredDate(Strings.toDate(args[8]));
        a.setFleetFlag(args[9]);
        a.setInsuredTitleName(args[10]);
        a.setInsuredTitleNameEn(args[11]);
        a.setInsuredName(args[12]);
        a.setInsuredNameEn(args[13]);
        a.setInsuredLastname(args[14]);
        a.setInsuredLastnameEn(args[15]);
        a.setMoobarn(args[16]);
        a.setRoomNumber(args[17]);
        a.setHomeNumber(args[18]);
        a.setMoo(args[19]);
        a.setSoi(args[20]);
        a.setRoad(args[21]);
        a.setTumbol(args[22]);
        a.setAmphur(args[23]);
        a.setProvince(args[24]);
        a.setPostCode(args[25]);
        a.setTelephoneExtension(args[26]);
        a.setTelephone(args[27]);
        a.setAccountKeyDwh(args[28]);
        a.setOccupation(args[29]);
        a.setModeOfPayment(args[30]);
        a.setPaymentChannel(args[31]);
        a.setCreditCardType(args[32]);
        a.setCreditCardNo(args[33]);
        a.setCardIssuedName(args[34]);
        a.setExpiryDate(args[35]);
        a.setCustomerType(args[36]);
        a.setCardType(args[37]);
        a.setCardNo(args[38]);
        a.setDetailNo(args[39]);
        a.setStatus(args[40]);
        a.setGender(args[41]);
        a.setMarriageStatus(args[42]);
        a.setCustomerKeyDwh(Strings.toDouble(args[43]));
        a.setChannelFlag(args[44]);
        a.setInsurerCode(args[45]);
        a.setPolicyYear(args[46]);
        a.setBaseCreditCard(args[47]);
        a.setSellingDate(Strings.toDate(args[48]));
        a.setCampaignCode(args[49]);
        return a;
    }

    @Override
    public boolean postFilter(ApplicationHd applicationHdOld) {
        return applicationHdOld != null;
    }
}
