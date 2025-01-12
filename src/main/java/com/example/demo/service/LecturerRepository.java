package com.example.demo.service;

import com.example.demo.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository <Lecturer,Long> {
}
