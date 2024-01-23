package com.rest_spring_mvn.directdebit;


import com.rest_spring_mvn.model.baybillingdetail.BillingDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/bay_billing")
@Slf4j
@RequiredArgsConstructor
public class DirectDebitController {

    private final BillingDetailMapperValue billingDetailMapperValue;


    @GetMapping("/to-ascii")
    public String encode2(@RequestParam String thaiString){
        byte[] thaiBytes = thaiString.getBytes();

        return new String(thaiBytes, StandardCharsets.US_ASCII);
    }

    @GetMapping("/to-utf8")
    public String encode1(@RequestParam String thaiString){
        byte[] thaiBytes = thaiString.getBytes();

        return new String(thaiBytes, StandardCharsets.UTF_8);
    }


    @GetMapping("/")
    public List<BillingDetail> fileReader () {return readerFile();}

    @GetMapping("/stream")
    public void streamReader () {
        readerFileStream();}


    private List<BillingDetail> readerFile(){
        var resultList = new ArrayList<BillingDetail>();
        var start = System.currentTimeMillis();
        try {
            File myObj = new File("txt/QGIB_XXX_PreImpBAY_DB_111028_100.txt");
            Scanner myReader = new Scanner(myObj);
            var count = 0;
            while (myReader.hasNextLine()) {
                var data = myReader.nextLine();
                count += 1;
                resultList.add(billingDetailMapperValue.mapperReader(data));
            }
            log.info("row_num : {}",count);
            logMemoryUsed();
            myReader.close();
        } catch (FileNotFoundException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }
        var totalMillis = System.currentTimeMillis() - start;
        log.info("total_time (ms) : {}", totalMillis);
        return resultList;
    }


    private void readerFileStream(){
        var start = System.currentTimeMillis();
        String fileName = "txt/QGIB_XXX_PreImpBAY_DB_111028_100.txt";
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

            var totalMillis = System.currentTimeMillis() - start;
            log.info("total_time (ms) : {}", totalMillis);
            logMemoryUsed();

        } catch (IOException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }

    private void logMemoryUsed(){
        Runtime rt = Runtime.getRuntime();
        long totalMem = rt.totalMemory();
        long freeMem = rt.freeMemory();
        long usedMem = totalMem - freeMem;
        log.info("Amount of used memory: {}" , usedMem/1024/1024 + "MB");
    }


    public static ByteBuffer toByteBuffer(String content, String encode) {
        Charset charset = Charset.forName(encode);
        return charset.encode(content);
    }
}
