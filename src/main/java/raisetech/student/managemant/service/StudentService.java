package raisetech.student.managemant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.managemant.data.Student;
import raisetech.student.managemant.data.StudentCourse;
import raisetech.student.managemant.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

 //機能を追加したいときに処理（コード）をわかりやすくするためにコントローラーではなくサービスのクラスを作成して処理を行う。
    public List<Student> searchStudentList() {

        //年齢が30代の人のみを抽出（課題）
        List<Student> students30s =new ArrayList<>();
        for (Student student : repository.searchStudent()){
            int age = student.getAge();
            if (age >= 30 && age <= 39){
                students30s.add(student);
            }
        }
        //抽出したリストをコントローラーに返す（課題）
          return students30s;

        //下記のコードは後で使うので残す。
       // return repository.searchStudent();
    }

    public List<StudentCourse> searchStudentCourseList() {

        //Javaコースのコース情報のみを抽出（課題）
        List<StudentCourse> studentCourseJava = repository.searchStudentCourses().stream().filter(course -> "Java".equals(course.getCourseName())).collect(Collectors.toList());

        //抽出したリストをコントローラーに返す（課題）
        return studentCourseJava;
        //return repository.searchStudentCourses();
    }
}
