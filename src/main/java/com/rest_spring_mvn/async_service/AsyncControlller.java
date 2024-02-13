package com.rest_spring_mvn.async_service;


import com.rest_spring_mvn.model.HashKey;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/v1/async")
public class AsyncControlller {
    @Autowired
    AsyncService asyncService;

    @GetMapping()
    public ResponseEntity<HashKey> applicationHeader() throws InterruptedException {
        asyncService.slowOperation();
        var hash = new HashKey();
        return ResponseEntity.ok(hash);
    }
}
