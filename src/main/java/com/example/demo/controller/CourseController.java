package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/Courses")
@RestController
@CrossOrigin
public class CourseController {
    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        Course c = courseRepository.findById(id).orElse(null);
        if (c == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody Course c) {
        Course newCourse = courseRepository.save(c);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Course> put(@PathVariable Long id, @RequestBody Course c) {
        Course c2 = courseRepository.findById(id).orElse(null);
        if (c2 == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        c2.setName(c.getName());
        c2.setDescription(c.getDescription());
        c2.setLecturer(c.getLecturer());
        c2.setCategory(c.getCategory());
        c2.setPrice(c.getPrice());
        courseRepository.save(c2);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Course c = courseRepository.findById(id).orElse(null);
        if (c == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        courseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
