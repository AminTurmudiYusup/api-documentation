package com.nostra.movies.controller;

import com.nostra.movies.entity.Category;
import com.nostra.movies.entity.Video;
import com.nostra.movies.exception.CategoryNotFoundException;
import com.nostra.movies.exception.InvalidRequestException;
import com.nostra.movies.exception.VideoNotFoundException;
import com.nostra.movies.repository.VideoRepository;
import com.nostra.movies.service.impl.VideoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class VideoController {

    Logger LOG= LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private VideoRepository videoRepository;

    @Operation(summary = "Get List Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list videos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Video.class)) }),
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content) })
    @GetMapping("/videos")
    List<Video> getAllVideos(){
        return videoRepository.findAll();
    }

    @GetMapping("/videos/search")
    List<Video> getVideoByKeyword(@RequestParam("keyword")String keyword){
        return videoService.searchByName(keyword);
    }

    @Operation(summary = "Add Videos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Video succes added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Video.class)) }) })
    @PostMapping("/videos")
    ResponseEntity<String> addNewVideo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("categoryId") String categoryId) throws IOException {
         videoService.saveVideo(file,name ,categoryId);
        return ResponseEntity.ok("Video saved SuccesFully");
    }

    @Operation(summary = "Get a video by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the video",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Video.class)) }),
            @ApiResponse(responseCode = "404", description = "Video Id not found",
                    content = @Content) })
    @GetMapping("/videos/{id}")
    Optional<Video> getVideoById(@PathVariable Long id){
        return Optional.ofNullable(videoService.findVideoClassById(id)
                .orElseThrow(() -> new VideoNotFoundException(id)));
    }

    @Operation(summary = "Delete a video by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Video Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Video.class)) }),
            @ApiResponse(responseCode = "404", description = "Video Id not found",
                    content = @Content) })
    @DeleteMapping("/videos/{id}")
    ResponseEntity<String> deleteVideo(@PathVariable Long id){
        if(videoRepository.findById(id).isEmpty()){
            throw new VideoNotFoundException(id);
        }
        videoRepository.deleteById(id);
       return ResponseEntity.ok("Video successfully deleted");
    }
}
