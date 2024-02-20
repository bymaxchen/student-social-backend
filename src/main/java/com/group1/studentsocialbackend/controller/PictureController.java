package com.group1.studentsocialbackend.controller;

import com.group1.studentsocialbackend.util.FileNameUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            byte[] bytes = file.getBytes();
            String filename=FileNameUtil.getFileName(file.getOriginalFilename());
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            Files.write(path, bytes);
            return new ResponseEntity<>("images/"+filename, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload '" + file.getOriginalFilename() + "'", HttpStatus.BAD_REQUEST);
        }
    }
}
