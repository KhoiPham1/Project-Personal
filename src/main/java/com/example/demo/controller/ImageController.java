package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<List<Image>> listAllImage(){
        List<Image> images = imageService.findAll();
        if (images.isEmpty()){
            return new ResponseEntity<List<Image>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Image>>(HttpStatus.OK);
    }

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

    @RequestMapping(value = "/image/{id}",method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> updateImage(@PathVariable("id") long id, @RequestBody Image image){
        System.out.println("Updating image" + id);
        Image currentImage = imageService.findById(id);
        if (currentImage == null){
            System.out.println("Image with id" + id + "not found");
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        currentImage.setImageName(image.getImageName());
        imageService.saveImage(currentImage);
        return new ResponseEntity<Image>(currentImage, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/image/{filename:.+}")
    public ResponseEntity<?> deleteFile (@PathVariable String nameImage){
        try {
            imageService.delete(nameImage);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success delete");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail delete");
        }
    }
}

