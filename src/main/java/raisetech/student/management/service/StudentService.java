package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.repository.StudentRepository;

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
        return repository.searchStudent();
    }

    public List<StudentCourse> searchStudentCourseList() {
        return repository.searchStudentCourses();
    }

        public void registerStudent(Student student) {
            repository.insertStudent(student);
    }
}