package com.example.demo.controller;

import com.example.demo.model.Lecturer;
import com.example.demo.service.LecturerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/Lecturer")
@RestController
@CrossOrigin
public class LecturerController {
    private LecturerRepository lecturerRepository;

    public LecturerController(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Lecturer> getById(@PathVariable Long id) {
        Lecturer l = lecturerRepository.findById(id).orElse(null);
        if (l == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Lecturer>> getAll() {
        return new ResponseEntity<>(lecturerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addLecturer")
    public ResponseEntity<Lecturer> addLecturer(@RequestBody Lecturer l) {
        Lecturer newLecturer = lecturerRepository.save(l);
        return new ResponseEntity<>(newLecturer, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Lecturer> put(@PathVariable Long id, @RequestBody Lecturer l) {
        Lecturer l2 = lecturerRepository.findById(id).orElse(null);
        if (l2 == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        l2.setName(l.getName());
        l2.setAdress(l.getAdress());
        l2.setPhone(l.getPhone());
        l2.setProfession(l.getProfession());
        l2.setYearsExperience(l.getYearsExperience());
        l2.setCourseList(l.getCourseList());
        lecturerRepository.save(l2);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Lecturer l = lecturerRepository.findById(id).orElse(null);
        if (l == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        lecturerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
