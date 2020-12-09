package com.example.Lab1.controller;

import com.example.Lab1.entity.Student;
import com.example.Lab1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students/")
public class MainController {

    private final StudentRepository studentRepository;

    @Autowired
    private MainController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("frontPage")
    public String showSignUpForm(Student student) {
        return "addStudent";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addStudent(Student student) {

        studentRepository.save(student);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "updateStudent";
        }
        studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid student Id:" + id));
        studentRepository.delete(student);
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

}
