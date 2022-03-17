package com.rest_spring_mvn;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
//        app.addInitializers(new PropertyPasswordDecodingContextInitializer());
        app.run(args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
    }

    @Bean
    public CommonsRequestLoggingFilter loggingFilter (){
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("request data:");
        return filter;
    }
}