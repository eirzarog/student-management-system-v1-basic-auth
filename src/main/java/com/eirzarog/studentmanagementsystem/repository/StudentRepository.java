package com.eirzarog.studentmanagementsystem.repository;

import com.eirzarog.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
