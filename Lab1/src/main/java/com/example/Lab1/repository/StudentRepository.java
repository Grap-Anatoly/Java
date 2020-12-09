package com.example.Lab1.repository;

import com.example.Lab1.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>  {
    List<Student> findByName(String name);
}
