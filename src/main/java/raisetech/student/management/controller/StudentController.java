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

/*
受講生の検索や登録・更新などを行うREST　APIとして受け付けるController
*/

@RestController
public class StudentController {

    private StudentService service;


    @Autowired
    public StudentController(StudentService service) {
        this.service = service;

    }

    /*
    受講生一覧検索です。
    全権検索を行うため、条件指定は行いません
    @return 受講生一覧（全権）
     */
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /*
    受講生検索です。
    IDに紐づく任意の受講生の情報を取得します
    @parm　id 受講生ID
    @return 受講生情報
     */
    @GetMapping("/student/{id}")
    public StudentDetail getStudent(@PathVariable String id) {
        return service.serchStudent(id);
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
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail){
        //service.registerStudent(studentDetail.getStudent());
        StudentDetail responseStudentDetail =service.registerStudent(studentDetail);
    return ResponseEntity.ok(studentDetail);
    }

    @PostMapping ("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }
}


