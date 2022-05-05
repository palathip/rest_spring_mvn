package com.rest_spring_mvn.app_interface_header;

import com.rest_spring_mvn.model.ApplicationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/application-interface-header")
public class AppInterfaceHd {

    @PostMapping("post")
    public List<ApplicationInterface> postInterface (@RequestBody List<ApplicationInterface> applicationInterface){
        return applicationInterface;
    }
}
