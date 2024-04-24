package org.xiayudeshu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xiayudeshu.StorageConfig;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    private StorageConfig storage;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            byte[] bytes = file.getBytes();
            String uploadDir = storage.getPath();
            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);
            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }
}
