package raisetech.student.management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

@Validated
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
    @return 受講生詳細一覧（全件）
     */
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /*
    受講生詳細の検索です。
    IDに紐づく任意の受講生の情報を取得します
    @parm　id 受講生ID
    @return 受講生情報
     */
    @GetMapping("/student/{id}")
    public StudentDetail getStudent(@PathVariable String id) {
        return service.serchStudent(id);
    }

    /*
            受講生を新規登録します
            IDに関しては自動採番を行う
            @parm　student　受講生
                     */
    @GetMapping("/newStudent")
    public String newStudent(Model model){
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourseList(Arrays.asList(new StudentCourse()));
        model.addAttribute("studentDetail", studentDetail);
        return "registerStudent";
    }

    /*
           受講生詳細の登録を行います。
           @parm　studentDetail 受講生詳細
           @return 実行結果
            */
    @PostMapping ("/registerStudent")
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail){
        StudentDetail responseStudentDetail =service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
    }

    /*
              受講生詳細の更新を行います
              キャンセルフラグの更新もここで行います（論理削除）
              @parm　studentDetail　受講生詳細
              @return　実行結果
                       */
    @PutMapping ("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }
}


