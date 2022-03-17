package com.rest_spring_mvn.application_token;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rest_spring_mvn.entity.ApplicationAuth;
import com.rest_spring_mvn.entity.ApplicationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//https://stackoverflow.com/questions/53803780/tests-in-spring-boot-with-database-h2
//https://nordicapis.com/10-best-practices-for-naming-api-endpoints/
@RestController
@Slf4j
@RequestMapping(path = "/v1/auth")
public class ApplicationTokenController {

    @Autowired
    ApplicationTokenService applicationTokenService;

    @GetMapping("/user_data")
    public ResponseEntity<List<ApplicationToken>> applicationTokenGet(@RequestBody ApplicationAuth applicationAuth) {
        return new ResponseEntity(applicationAuth, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<ApplicationToken>> applicationHeaderAll() {

        return new ResponseEntity("waiting result", HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<List<ApplicationToken>> applicationHeader(
            @RequestBody JSONPObject auth) throws IOException {
        return new ResponseEntity("waiting result", HttpStatus.OK);
    }
}
