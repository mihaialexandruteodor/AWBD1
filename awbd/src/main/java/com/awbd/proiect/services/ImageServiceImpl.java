package com.awbd.proiect.services;

import com.awbd.proiect.domain.Info;
import com.awbd.proiect.domain.Product;
import com.awbd.proiect.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{
    ProductRepository productRepository;

    @Autowired
    public ImageServiceImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long productId, MultipartFile file) {
        try {
            Product product = productRepository.findById(productId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0; for (byte b : file.getBytes()){
                byteObjects[i++] = b; }

            Info info = product.getInfo();
            if (info == null) {
                info = new Info();
            }

            info.setImage(byteObjects);
            product.setInfo(info);
            info.setProduct(product);
            productRepository.save(product); }
        catch (IOException e) {
        }
    }
}
