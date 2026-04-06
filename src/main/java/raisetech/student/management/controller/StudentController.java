package raisetech.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourses;
import raisetech.student.management.service.StudentService;

import java.util.List;


@Controller
public class StudentController {

    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }



    @GetMapping("/studentList")
    public String getStudentList(Model model) {
        List<Student> students = service.searchStudentList();
        List<StudentCourses> studentCourses = service.searchStudentCourseList();

        model.addAttribute("StudentList",converter.convertStudentDetails(students,studentCourses));
        return "StudentList";
    }



    @GetMapping("/studentCourseList")
    public String getStudenCoursetList(Model model) {
        List<StudentCourses> studentCourses = service.searchStudentCourseList();

        model.addAttribute("StudentCourseList",studentCourses);
        return "StudentCourseList";
    }
}

