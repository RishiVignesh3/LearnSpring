package com.example.Student.controller;


import com.example.Student.model.Image;
import com.example.Student.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setData(file.getBytes());
            imageRepository.save(image);
            return new ResponseEntity<>("Image uploaded successfully!", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return  new ResponseEntity<>("Failed to upload image!", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public byte[] getImage(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        return image != null ? image.getData() : new byte[0];
    }
}

