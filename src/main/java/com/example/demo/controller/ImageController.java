package com.example.demo.controller;

import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public ResponseEntity<?> createImage(@RequestParam(name = "file") MultipartFile file, HttpServletRequest servletRequest) throws URISyntaxException {
        try {
            imageService.create(file);
            URI locationUri = new URI(servletRequest.getRequestURI().toString() + "/").resolve(file.getOriginalFilename() +"/raw");
            return ResponseEntity.created(locationUri).body("success upload");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail" + "=>" + e.getMessage());
        }
    }
}

