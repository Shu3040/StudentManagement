package raisetech.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

import java.util.Arrays;
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
        List<StudentCourse> studentCourse = service.searchStudentCourseList();

        model.addAttribute("studentList",converter.convertStudentDetails(students,studentCourse));
        return "studentList";
    }

    @GetMapping("/Student/{id}")
    public String getStudent(@PathVariable String id, Model model) {
        StudentDetail studentDetail =service.serchStudent(id);
        model.addAttribute("studentDetail", studentDetail);
        return "updateStudent";
    }


    /*@GetMapping("/studentCourseList")
    public String getStudentCourseList(Model model) {
        List<StudentCourse> studentCourses = service.searchStudentCourseList();

        model.addAttribute("studentCourseList",studentCourses);
        return "studentCourseList";
    }*/

    @GetMapping("/newStudent")
    public String newStudent(Model model){
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourse(Arrays.asList(new StudentCourse()));
        model.addAttribute("studentDetail", studentDetail);
        return "registerStudent";
    }

    @PostMapping ("/registerStudent")
    public  String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result){
        if (result.hasErrors()){
            return "registerStudent";
        }

        //service.registerStudent(studentDetail.getStudent());
        service.registerStudent(studentDetail);

        System.out.println(studentDetail.getStudent().getName()+"さんが新規受講生として登録されました。");
    return "redirect:/studentList";
    }

    @PostMapping ("/updateStudent")
    public  String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
        if (result.hasErrors()) {
            return "updateStudent";
        }
        service.updateStudent(studentDetail);
        return "redirect:/studentList";
    }
}


