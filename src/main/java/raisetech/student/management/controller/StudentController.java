package raisetech.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

import java.util.Arrays;
import java.util.List;


@RestController
public class StudentController {

    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentCourse> studentCourse = service.searchStudentCourseList();
        return converter.convertStudentDetails(students,studentCourse);
    }

    @GetMapping("/student/{id}")
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
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }
}


