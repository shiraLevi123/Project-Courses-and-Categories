package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/Category")
@RestController
@CrossOrigin
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

   @GetMapping("/getById/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category c = categoryRepository.findById(id).orElse(null);
        if (c == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category c) {
        Category newCategory = categoryRepository.save(c);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Category> put(@PathVariable Long id, @RequestBody Category c) {
        Category c2 = categoryRepository.findById(id).orElse(null);
        if (c2 == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        c2.setName(c.getName());
        c2.setDescription(c.getDescription());
        c2.setCourseList(c.getCourseList());
        categoryRepository.save(c2);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Category c =categoryRepository.findById(id).orElse(null);
        if (c == null)
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        categoryRepository.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}