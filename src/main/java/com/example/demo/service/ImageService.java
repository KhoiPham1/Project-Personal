package com.example.demo.service;

import com.example.demo.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService  {
    //trả về danh sách ảnh
    List<Image> findAll();

    //trả về ảnh với tên truyền vào
    Resource findOneImage(String name);

    //tạo ảnh mới
    void create(MultipartFile file) throws IOException;

    Image findById (Long id);

    void saveImage(Image image);

    //xóa ảnh
    void delete(String name) throws IOException;
}
