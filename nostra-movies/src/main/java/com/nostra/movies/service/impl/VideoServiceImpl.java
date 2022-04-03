package com.nostra.movies.service.impl;

import com.nostra.movies.entity.Category;
import com.nostra.movies.entity.Video;
import com.nostra.movies.repository.VideoRepository;
import com.nostra.movies.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {

    private VideoRepository videoRepository;

    public Optional<Video> getVideo(Video video) {
        return videoRepository.findById(video.getId());
    }

    @Override
    public Video saveVideo(MultipartFile file, String name, String categoryId) throws IOException {
        Video newVid = new Video();
        newVid.setName(name);
        Category category=new Category();
        category.setId(Long.valueOf(categoryId));
        newVid.setCategory(category);
        newVid.setData(file.getBytes());
        videoRepository.save(newVid);
        return newVid;
    }

    @Override
    public Optional<Video> findVideoClassById(Long id) {
        return videoRepository.findById(id);
    }

    @Override
    public List<Video> searchByName(String keyword) {
        if(keyword!=null){
            videoRepository.search(keyword);
        }
        return videoRepository.findAll();
    }
}
