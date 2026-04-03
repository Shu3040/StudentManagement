package raisetech.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.service.StudentService;

import java.util.List;

@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }


    @GetMapping("/studentList")
    public List<Student> getStudentList() {
        return service.searchStudentList();
    }

    @GetMapping("/studentCourseList")
    public List<StudentCourse> getStudentCourseList() {
        return service.searchStudentCourseList();
    }
}

