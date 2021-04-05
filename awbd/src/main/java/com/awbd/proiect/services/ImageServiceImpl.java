package com.awbd.proiect.services;

import com.awbd.proiect.domain.Info;
import com.awbd.proiect.domain.Movie;
import com.awbd.proiect.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{
    MovieRepository movieRepository;

    @Autowired
    public ImageServiceImpl(MovieRepository movieRepository) {

        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long productId, MultipartFile file) {
        try {
            Movie movie = movieRepository.findById(productId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0; for (byte b : file.getBytes()){
                byteObjects[i++] = b; }

            Info info = movie.getInfo();
            if (info == null) {
                info = new Info();
            }

            info.setImage(byteObjects);
            movie.setInfo(info);
            info.setMovie(movie);
            movieRepository.save(movie); }
        catch (IOException e) {
        }
    }
}
