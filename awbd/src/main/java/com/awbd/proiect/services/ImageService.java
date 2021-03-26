package com.awbd.proiect.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long productId, MultipartFile file);
}
