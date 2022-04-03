package com.nostra.movies.repository;

import com.nostra.movies.entity.Category;
import com.nostra.movies.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("SELECT p FROM Video p WHERE p.name LIKE %?1%")
    public List<Video> search(String keyword);
}
