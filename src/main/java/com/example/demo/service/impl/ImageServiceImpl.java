package com.example.demo.service.impl;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ImageServiceImpl implements ImageService {
    private String UPLOAD = "upload-image";
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<Image> findAll() {
        return (List<Image>) imageRepository.findAll();
    }

    @Override
    public Resource findOneImage(String name) {
        return resourceLoader.getResource("file:" + UPLOAD + "/" +name);
    }

    @Override
    public void create(MultipartFile file) throws IOException {
// getinputstream lấy chuỗi kí tự để cấu hình thành 1 ảnh
        //getoriginalfilename để lưu tên ảnh
         if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD, file.getOriginalFilename()));
            imageRepository.save(new Image(file.getOriginalFilename()));
        }

    }

    @Override
    public void delete(String name) throws IOException {
        Image byName = imageRepository.findByName(name);
        imageRepository.delete(byName);
        Files.deleteIfExists(Paths.get(UPLOAD, name));
        //ddeer xóa ảnh trong file upload-image
    }
}
