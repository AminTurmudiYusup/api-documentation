package com.nostra.movies.service;

import com.nostra.movies.entity.Video;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface VideoService {
    Video saveVideo(MultipartFile file, String name, String categoryId) throws IOException;
    public Optional<Video> findVideoClassById(Long id);
    public List<Video> searchByName(String keyword);
}
