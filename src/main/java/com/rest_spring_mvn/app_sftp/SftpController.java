package com.rest_spring_mvn.app_sftp;


import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.userauth.keyprovider.KeyProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class SftpController {
    @GetMapping(value="/testData")
    public ResponseEntity<Object> getCredentialFcm(){
            try {
                File myObj = new File("D:\\Users\\513007133\\Downloads\\filename.txt");

                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            try {
                FileWriter myWriter = new FileWriter("D:\\Users\\513007133\\Downloads\\filename.txt");
                myWriter.write("Files in Java might be tricky, but it is fun enough! 55555");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            ///////////////////////////////////////////////////////

            String remoteHost = "aycapsw01ts602";
            String username = "th-svc-ibb_admin";
            //String password = "PASSWORD_HERE";

            SSHClient client = new SSHClient();
            client.addHostKeyVerifier(new PromiscuousVerifier());
            File privateKey = new File("D:\\codeSpace\\kbb-service\\src\\main\\resources\\key\\aycapsw01ts602.pk");

            KeyProvider keys;

            try {

                client.connect(remoteHost, 22);
                keys = client.loadKeys(privateKey.getPath());
                client.authPublickey(username, keys);
                System.out.println("Is Authen " + client.isAuthenticated());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String localFile = "D:\\Users\\513007133\\Downloads\\filename.txt";
            String remoteDir = "/cygdrive/d/IBS_Storage/KBB/";

            // upload

            try {
                SFTPClient sftpClient = client.newSFTPClient();
                sftpClient.put(localFile, remoteDir + "TestFileSFTP.txt");
                sftpClient.close();
                client.disconnect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ResponseEntity.ok("Created");
        }
    }
