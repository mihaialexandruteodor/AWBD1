package com.awbd.lab7.controllers;

import com.awbd.lab7.domain.Info;
import com.awbd.lab7.domain.Product;
import com.awbd.lab7.services.ImageService;
import com.awbd.lab7.services.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ProductService productService;


    public ImageController(@Autowired ImageService imageService, @Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/getimage/{id}")
    public void downloadImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        Product product = productService.findById(Long.valueOf(id));
        if (product.getInfo() != null) {
            Info info = product.getInfo();

            if (product.getInfo().getImage() != null) {
                byte[] byteArray = new byte[info.getImage().length];
                int i = 0;
                for (Byte wrappedByte : info.getImage()) {
                    byteArray[i++] = wrappedByte;
                }
                response.setContentType("image/jpeg");
                InputStream is = new ByteArrayInputStream(byteArray);
                try {
                    IOUtils.copy(is, response.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}