package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;
/*
    受講生情報を取り扱うサービス
    受講生の検索や登録、更新処理を行います。
     */

@Service
public class StudentService {

    private StudentRepository repository;

    private StudentConverter converter;

    @Autowired
    public StudentService(StudentRepository repository, StudentConverter studentConverter) {
        this.repository = repository;
        this.converter = studentConverter;
    }

    /*
        受講生詳細の一覧検索です。
        全権検索を行うため、条件師弟は行いません
        @return 受講生詳細一覧（全件）
         */
    public List<StudentDetail> searchStudentList() {
        List<Student> studentList = repository.search();
        List<StudentCourse> studentCourseList = repository.searchStudentCoursesList();
        return converter.convertStudentDetails(studentList, studentCourseList);
    }

    /*
    受講生詳細の検索です。
    IDに紐づく任意の受講生の情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します
    @return 受講生情報
     */
    public StudentDetail searchStudent(String id) {
        Student student = repository.searchStudent(id);
        List<StudentCourse> studentCourses = repository.searchStudentCourse(student.getId());
        return new StudentDetail(student, studentCourses);
    }

    /*
     受講生コース情報の全件検索です。
     @parm　id 受講生ID
     @return 受講生情報
     */
    public List<StudentCourse> searchStudentCourseList() {
        return repository.searchStudentCoursesList();
    }

    /*
        受講生詳細の登録を行います。
        受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値とコース開始日、コース終了日を設定します
        @parm　studentDetail 受講生詳細
        @return 登録情報を付与した受講生詳細
         */
    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail) {
        Student student = studentDetail.getStudent();

        repository.registerStudent(student);
        studentDetail.getStudentCourseList().forEach(studentCourse -> {
            initStudentCourse(studentCourse, student);
            repository.registerStudentCourse(studentCourse);
        });
        return studentDetail;
    }
    /*
     受講生コース情報を登録する際の初期情報を設定する
     @parm　studentCourse　受講生コース情報
     @parm  student　受講生
     */
    private void initStudentCourse(StudentCourse studentCourse, Student student) {
        LocalDateTime now = LocalDateTime.now();

        studentCourse.setStudentId(student.getId());
        studentCourse.setStartDate(now);
        studentCourse.setEndDate(now.plusYears(1));
    }

    /*
     受講生詳細の更新を行います
     受講生と受講生コース情報をそれぞれ更新します。
     @parm　studentDetail　受講生詳細
     */
    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        studentDetail.getStudentCourseList()
                .forEach(studentCourse -> repository.updateStudentCourse(studentCourse));
    }
}
