package com.rest_spring_mvn.application_header;

import com.rest_spring_mvn.entity.ApplicationHd;
import com.rest_spring_mvn.mapper.TextFileReader;
import com.rest_spring_mvn.repository.ApplicationHdRepository;
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
public class ApplicationHdService {
    @Autowired
    TextFileReader<ApplicationHd> textFileReader;

    @Autowired
    ApplicationHdRepository applicationHdRepository;

    public List<ApplicationHd> get() {
        return applicationHdRepository.findTop10By();
    }

    public List<ApplicationHd> getAll() {
        return applicationHdRepository.findTop20By();
    }

    public List<ApplicationHd> post(MultipartFile mFile) throws IOException {
        Path temp = Files.createTempFile("ApplicationHd_", ".txt");
        mFile.transferTo(temp);
        List<ApplicationHd> apps =
                applicationHdRepository.saveAllAndFlush(textFileReader.supplier(temp).get().collect(Collectors.toList()));
        if (!Files.deleteIfExists(temp)) log.info("File doesn't exist.");
        return apps.subList(0, 10);
    }
}
