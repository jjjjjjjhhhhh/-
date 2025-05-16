package com.zhhub.controller;




import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author zql
 * <p>
 * 2024/4/30
 */



@RestController
public class UploadAvatarController {

    private static final String UPLOAD_DIR = "D:\\xiangmu\\hczl\\hczl-spring\\src\\main\\resources\\static\\";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@Validated @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            byte[] bytes = file.getBytes();
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String fileName = new Date().getTime()+"."+fileExt;
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, bytes);
            file.transferTo(path);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.ok("错误");
        }
    }
}
