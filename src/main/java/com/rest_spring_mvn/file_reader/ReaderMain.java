package com.rest_spring_mvn.file_reader;

import com.rest_spring_mvn.model.reader.SqlReaderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping(path = "/v1/excel")
public class ReaderMain {
    @PostMapping("/convert_to_sql")
    public SqlReaderResult postInterface (@RequestParam MultipartFile file,
                                          Integer numberOfSheet) throws IOException {

        return ReaderService.reader(file,numberOfSheet);
    }
}
