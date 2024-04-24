package org.xiayudeshu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xiayudeshu.Result;
import org.xiayudeshu.StorageConfig;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    private StorageConfig storage;

    @PostMapping("/upload")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("Please select a file to upload.");
        }
        try {
            byte[] bytes = file.getBytes();
            String uploadDir = storage.getPath();
            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);
            return Result.success("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("File upload failed!");
        }
    }
}
