package com.rest_spring_mvn.hash;

import com.rest_spring_mvn.model.HashKey;
import com.rest_spring_mvn.utility.HashTxtUtility;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
@Slf4j
@RequestMapping(path = "/v1/hash")
public class hashtool {

    @PostMapping()
    public void hash (@RequestBody HashKey hashKey){

        hashKey.getCitizen().forEach(row->{
            var custkey = "";
            try {
                custkey = HashTxtUtility.build(row);
            } catch (Exception e) {
                log.info("can not hash citizen");
            }

            String finalCustkey = custkey;
            System.out.println("INSERT INTO KBB_CUSTKEY(CUSTKEY,CITIZEN_ID) VALUES ('"+finalCustkey+"','"+row+"');");
        });



    }
}
