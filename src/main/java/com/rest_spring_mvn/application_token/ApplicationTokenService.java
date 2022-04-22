package com.rest_spring_mvn.application_token;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.rest_spring_mvn.model.ApplicationAuth;
import com.rest_spring_mvn.entity.ApplicationToken;
import com.rest_spring_mvn.model.ApplicationData;
import com.rest_spring_mvn.repository.ApplicationTokenRepository;
import com.rest_spring_mvn.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


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
                applicationData.setResponseDesc("Success");
                HashMap <String,String> system = new HashMap();
                system.put("systemId","S0014");
                system.put("systemName","IAM");
                ArrayList systemList = new ArrayList();
                systemList.add(system);
                applicationData.setSystemList(systemList);
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
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime exp = LocalDateTime.now().plusYears(1);
            applicationToken.setEffectiveDate(dtf.format(now));
            applicationToken.setExpireDate(dtf.format(exp));
            applicationHdRepository.saveAndFlush(applicationToken);
            return applicationToken;
        }
        else
            return "username already exit";
    }

    public Object decoder (String code) throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://boombibu-oidc-server.herokuapp.com/oauth2/token?" +
                        "grant_type=authorization_code" +
                        "&code=" + code +
                        "&scope=openid" +
                        "&redirect_uri=http://localhost:8080/api/v1/auth/oidc/decoder/token")
                .header("Authorization", "Basic aWFtLWlkOmlhbS1zZWNyZXQ=")
                .asString();
        JSONObject responseData = new JSONObject(response.getBody());
        System.out.println(response.getBody());
        String token = (String) responseData.get("access_token");

        String[] stringList = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(stringList[1]));
        System.out.println(payload);
        JSONObject information = new JSONObject(payload);

        List<ApplicationToken> checkUsername = applicationHdRepository.findByOpenIdEquals((String) information.get("sub"));
        if (checkUsername.isEmpty()) {
            ApplicationToken oidcData = new ApplicationToken();
            oidcData.setOpenId((String) information.get("sub"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime exp = LocalDateTime.now().plusYears(1);
            oidcData.setEffectiveDate(dtf.format(now));
            oidcData.setExpireDate(dtf.format(exp));
            applicationHdRepository.saveAndFlush(oidcData);
            return getAuthByOpenId((String) information.get("sub"));
        }
        else{
            return getAuthByOpenId((String) information.get("sub"));
    }
    }

    public Object getAuthByOpenId(String openId) {
        List<ApplicationToken> userData = applicationHdRepository.findByOpenIdEquals(openId);
        if(!userData.isEmpty()) {
            ApplicationData applicationData = new ApplicationData();
            applicationData.setToken("DUMMY_TOKEN");
            applicationData.setEffectiveDate(userData.get(0).getEffectiveDate());
            applicationData.setExpireDate(userData.get(0).getExpireDate());
            applicationData.setOpenId(userData.get(0).getOpenId());
            applicationData.setResponseCode("S00001");
            applicationData.setResponseDesc("Success");
            HashMap <String,String> system = new HashMap();
            system.put("systemId","S0014");
            system.put("systemName","IAM");
            ArrayList systemList = new ArrayList();
            systemList.add(system);
            applicationData.setSystemList(systemList);
            return applicationData;
        }
        else {
            return userData;
        }
    }

}
