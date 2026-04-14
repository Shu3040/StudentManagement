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

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    //機能を追加したいときに処理（コード）をわかりやすくするためにコントローラーではなくサービスのクラスを作成して処理を行う。
    public List<Student> searchStudentList() {
        return repository.search();
    }

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
