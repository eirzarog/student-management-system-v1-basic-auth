package com.eirzarog.studentmanagementsystem.controller;

import com.eirzarog.studentmanagementsystem.entity.Student;
import com.eirzarog.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/students";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "students/add";
        }
        studentService.saveStudent(student);
        return "redirect:/students/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "students/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult result) {
        if (result.hasErrors()) {
            student.setId(id);
            return "students/edit";
        }
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students/students";
    }
}