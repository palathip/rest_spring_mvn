package com.rest_spring_mvn.application_token;

import com.rest_spring_mvn.entity.ApplicationToken;
import com.rest_spring_mvn.mapper.TextFileReader;
import com.rest_spring_mvn.repository.ApplicationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApplicationTokenService {

    @Autowired
    ApplicationTokenRepository applicationHdRepository;

    public List<ApplicationToken> get() {
        return applicationHdRepository.findTop10By();
    }

    public List<ApplicationToken> getAll() {
        return applicationHdRepository.findTop20By();
    }

}
