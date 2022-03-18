package com.rest_spring_mvn.application_token;

import com.rest_spring_mvn.model.ApplicationAuth;
import com.rest_spring_mvn.entity.ApplicationToken;
import com.rest_spring_mvn.model.ApplicationData;
import com.rest_spring_mvn.repository.ApplicationTokenRepository;
import com.rest_spring_mvn.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Slf4j
public class ApplicationTokenService {

    @Autowired
    ApplicationTokenRepository applicationHdRepository;

    public List<ApplicationToken> getAll() {
        return applicationHdRepository.findAll();
    }

    public Object getAuth(ApplicationAuth applicationAuth) throws NoSuchAlgorithmException {
            List<ApplicationToken> userData = applicationHdRepository.findByUserNameEquals(applicationAuth.getUsername());
            String mdHash = MD5.getMD5(applicationAuth.getPassword());
            List<ApplicationToken> passwordData = applicationHdRepository.findByPassWordEquals(mdHash);
            if(!userData.isEmpty() && !passwordData.isEmpty()) {
                ApplicationData applicationData = new ApplicationData();
                applicationData.setToken("DUMMY_TOKEN");
                applicationData.setEffectiveDate(userData.get(0).getEffectiveDate());
                applicationData.setExpireDate(userData.get(0).getExpireDate());
                applicationData.setUserName(userData.get(0).getUserName());
                applicationData.setThaiFirstName(userData.get(0).getThaiFirstName());
                applicationData.setThaiLastName(userData.get(0).getThaiLastName());
                applicationData.setEngFirstName(userData.get(0).getEngFirstName());
                applicationData.setEngLastName(userData.get(0).getEngLastName());
                applicationData.setMobilePhone(userData.get(0).getMobilePhone());
                applicationData.setResponseCode("S00001");
                applicationData.setResponseDesc("Your password expires, please change password within extended date.");
                return applicationData;
            }
            else {
                return "username & password invalid";
            }
        }


    public Object post (ApplicationToken applicationToken) throws NoSuchAlgorithmException {
        List checkUsername = applicationHdRepository.findByUserNameEquals(applicationToken.getUserName());
        if (checkUsername.isEmpty()) {
            String mdHash = MD5.getMD5(applicationToken.getPassWord());
            applicationToken.setPassWord(mdHash);
            applicationHdRepository.saveAndFlush(applicationToken);
            return applicationToken;
        }
        else
            return "username already exit";
    }

}
