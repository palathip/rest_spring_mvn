package com.rest_spring_mvn;

import com.rest_spring_mvn.entity.ApplicationHd;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url() {
        return "http://localhost:" + port + "/v1/bulk-loader/application-header";
    }

    @Test
    void testGet() {
        ResponseEntity<ApplicationHd[]> response = restTemplate.getForEntity(url(), ApplicationHd[].class);
        assertEquals(0, response.getBody() != null ? 0 : response.getBody().length, "Payload must empty");
    }

    @Test
    void testUpload() {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        parameters.add("file", new ClassPathResource("THE_108_000.TXT"));
        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url(), entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}