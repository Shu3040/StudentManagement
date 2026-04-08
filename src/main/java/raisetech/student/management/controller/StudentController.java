package raisetech.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourses;
import raisetech.student.management.domain.StudentDetail;
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

        model.addAttribute("studentList",converter.convertStudentDetails(students,studentCourses));
        return "studentList";
    }



    @GetMapping("/studentCourseList")
    public String getStudentCourseList(Model model) {
        List<StudentCourses> studentCourses = service.searchStudentCourseList();

        model.addAttribute("studentCourseList",studentCourses);
        return "studentCourseList";
    }

    @GetMapping("/newStudent")
    public String newStudent(Model model){
        model.addAttribute("studentDetail", new StudentDetail());
        return "registerStudent";
    }



    @PostMapping ("/registerStudent")
    public  String registerStudemt(@ModelAttribute StudentDetail studentDetail, BindingResult result){
        if (result.hasErrors()){
            return "registerStudent";
        }
        System.out.println(studentDetail.getStudent().getName()+"さんが新規受講生として登録されました。");
    return "redirect:/studentList";
    }
}

