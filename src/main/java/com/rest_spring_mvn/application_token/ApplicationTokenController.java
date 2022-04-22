package com.rest_spring_mvn.application_token;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.rest_spring_mvn.model.ApplicationAuth;
import com.rest_spring_mvn.entity.ApplicationToken;
import com.rest_spring_mvn.model.ApplicationData;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

//https://stackoverflow.com/questions/53803780/tests-in-spring-boot-with-database-h2
//https://nordicapis.com/10-best-practices-for-naming-api-endpoints/
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/auth")
public class ApplicationTokenController {

    @Autowired
    ApplicationTokenService applicationTokenService;

    @GetMapping("/user_data")
    public ResponseEntity<List<ApplicationToken>> applicationTokenGet() {
        return new ResponseEntity(applicationTokenService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<ApplicationToken>> applicationHeaderAll(@RequestBody ApplicationToken applicationToken) throws NoSuchAlgorithmException {
        return new ResponseEntity(applicationTokenService.post(applicationToken), HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<List<ApplicationData>> applicationHeader(@RequestBody ApplicationAuth applicationAuth) throws IOException, NoSuchAlgorithmException {
        return new ResponseEntity(applicationTokenService.getAuth(applicationAuth), HttpStatus.OK);
    }

    @GetMapping("/oidc/decoder/token")
    public ResponseEntity<JSONObject> applicationDecoder(@RequestParam String code) throws UnirestException {
        return new ResponseEntity(applicationTokenService.decoder(code),HttpStatus.OK);
    }
}
