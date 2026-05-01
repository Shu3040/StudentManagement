package raisetech.student.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.exception.TestException;
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
    全件検索を行うため、条件指定は行いません
    @return 受講生詳細一覧（全件）
     */
    @Operation(summary = "受講生一覧検索", description = "受講生の一覧を検索します。",
            responses = {@ApiResponse(responseCode = "200", description = "受講生詳細一覧（全件）",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = StudentDetail.class)
                    )
            )
            )
    }
    )
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /*
    受講生コース情報の全件検索です。
    全件検索を行うため、条件指定は行いません
    @return 受講生コース情報一覧（全件）
     */
    @Operation(summary = "受講コース一覧検索", description = "受講生のコース情報の一覧検索します。")
    @GetMapping("/studentCourseList")
    public List<StudentCourse> getStudentCourseList()throws TestException {
        throw new TestException("例外処理を発生させています。");
        //return service.searchStudentCourseList();
    }

    /*
    受講生詳細の検索です。
    IDに紐づく任意の受講生の情報を取得します
    @parm　id 受講生ID
    @return 受講生情報
     */
    @Operation(summary = "受講生検索", description = "受講生を検索します。")
    @GetMapping("/student/{id}")
    public StudentDetail getStudent(@PathVariable String id) {
        return service.searchStudent(id);
    }

    /*
    受講生を新規登録します
    IDに関しては自動採番を行う
    @parm　student　受講生
     */
    @Operation(summary = "受講生登録", description = "受講生を登録します。")
    @GetMapping("/newStudent")
    public ResponseEntity<StudentDetail> newStudent(){
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourseList(Arrays.asList(new StudentCourse()));
        return ResponseEntity.ok(studentDetail);
    }

    /*
    受講生詳細の登録を行います。
    @parm　studentDetail 受講生詳細
    @return 実行結果
     */
    @Operation(summary = "受講生詳細登録", description = "受講生の詳細を登録します。")
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
    @Operation(summary = "受講生詳細更新", description = "受講生の詳細情報を更新します。")
    @PutMapping ("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }

}


