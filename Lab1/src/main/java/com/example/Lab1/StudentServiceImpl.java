package com.example.Lab1;

import com.example.Lab1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("studentService")
public class StudentServiceImpl {
    @Autowired
    private StudentRepository studentRepository;
}
