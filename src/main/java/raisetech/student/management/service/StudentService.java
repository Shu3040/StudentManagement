package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
/*
    受講生一覧検索です。
    全権検索を行うため、条件師弟は行いません
    @return 受講生一覧（全権）
     */
    public List<Student> searchStudentList() {
        return repository.search();
    }

    /*
    受講生検索です。
    IDに紐づく任意の受講生の情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します
    @parm　id 受講生ID
    @return 受講生情報
     */
    public StudentDetail serchStudent(String id){
        //System.out.println("id=" + id);
        Student student = repository.searchStudent(id);
        //System.out.println("student=" + student);
        List<StudentCourse> studentCourses = repository.searchStudentsCourses(student.getId());
        //System.out.println("studentCourses=" + studentCourses);
        StudentDetail studnetDetail = new StudentDetail();
        studnetDetail.setStudent(student);
        studnetDetail.setStudentCourse(studentCourses);
        //System.out.println("course size=" + studnetDetail.getStudentCourse().size());
        return studnetDetail;
    }

    public List<StudentCourse> searchStudentCourseList() {
        return repository.searchStudentCoursesList();
    }

    /*
        受講生検索です。
        IDに紐づく任意の受講生の情報を取得します
        @parm　id 受講生ID
        @return 受講生情報
         */
@Transactional
      //  public void registerStudent(Student student) {
            public StudentDetail registerStudent(StudentDetail studentDetail) {
    //       repository.insertStudent(student);
    repository.registerStudent(studentDetail.getStudent());

    for (StudentCourse studentCourse : studentDetail.getStudentCourse()) {
        studentCourse.setStudentId(studentDetail.getStudent().getId());
        studentCourse.setStartDate(LocalDateTime.now());
        studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

        repository.registerStudentCourse(studentCourse);
    }
    return studentDetail;
}

    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        for (StudentCourse studentCourse : studentDetail.getStudentCourse()){
            studentCourse.setStudentId(studentDetail.getStudent().getId());
            repository.updateStudentCourse(studentCourse);
        }
}
}
