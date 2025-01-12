package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {


}
