package com.rest_spring_mvn.omise;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;


@RestController
@RequestMapping("api/v1/omise")
public class Omise {


    @GetMapping("/")
    public String omiseTest (){

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("aycap-proxy-default.aycap.bayad.co.th", 80));
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.omise.co/charges?order=reverse_chronological",
                HttpMethod.GET,
                getRequestEntity("skey_test_5ulflppkxx4ymc9dki3"),
                String.class,
                1
        );

        if (response.getStatusCode() == HttpStatus.OK) {

            System.out.println(response.getBody());
        } else {

            System.out.println(response.getStatusCode());
        }

        return "success";
    }

    private HttpEntity<Object> getRequestEntity(String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(key,"");
        return new HttpEntity<>(headers);
    }
}
