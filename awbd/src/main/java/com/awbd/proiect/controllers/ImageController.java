package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Info;
import com.awbd.proiect.domain.Movie;
import com.awbd.proiect.services.ImageService;
import com.awbd.proiect.services.MovieService;
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

    private final MovieService movieService;


    public ImageController(@Autowired ImageService imageService, @Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movie/getimage/{id}")
    public void downloadImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        Movie movie = movieService.findById(Long.valueOf(id));
        if (movie.getInfo() != null) {
            Info info = movie.getInfo();

            if (movie.getInfo().getImage() != null) {
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