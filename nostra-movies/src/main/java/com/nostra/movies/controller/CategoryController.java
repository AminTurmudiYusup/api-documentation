package com.nostra.movies.controller;

import com.nostra.movies.entity.Category;
import com.nostra.movies.exception.CategoryNotFoundException;
import com.nostra.movies.exception.EmptyContentException;
import com.nostra.movies.exception.InvalidRequestException;
import com.nostra.movies.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    Logger LOG= LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Operation(summary = "Get List Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list category",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content) })
    @GetMapping("/categories")
    List<Category> getAllCategories(){
        if(categoryRepository.findAll().isEmpty()){
            throw new EmptyContentException("No Content Found");
        }
        return categoryRepository.findAll();
    }

    @Operation(summary = "Add Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "category succes added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class)) }) })
    @PostMapping("/categories")
    Category addNewCategory(@RequestBody @Valid Category category){
        return categoryRepository.save(category);
    }

    @Operation(summary = "Get a category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the category",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "404", description = "Category Id not found",
                    content = @Content) })
    @GetMapping("/categories/{id}")
    Category getCategoryyId(@PathVariable Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(id));
    }

    @Operation(summary = "Update a category bys id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Succes Updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "404", description = "Category Id not found",
                    content = @Content) })
    //update bisa pakai Put ataupun Patch, @PathVariable Long id
    @PutMapping("/categories")
    Category updateCategory(@RequestBody Category newCategory){
        if(newCategory==null||newCategory.getId()==null){
            throw new InvalidRequestException("Category or ID must not be null!");
        }
        return categoryRepository.findById(newCategory.getId())
                .map(category -> {
                    if (category==null){
                        new CategoryNotFoundException(newCategory.getId());
                    }else {
                        category.setName(newCategory.getName());
                        return categoryRepository.save(category);
                    }
                    return newCategory;
                })
                .orElseThrow(()-> new CategoryNotFoundException(newCategory.getId()));
    }

    @Operation(summary = "Delete a category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "404", description = "Category Id not found",
                    content = @Content) })
    @DeleteMapping("/categories/{id}")
    void deleteCategory(@PathVariable Long id){
        if(categoryRepository.findById(id).isEmpty()){
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }

}
