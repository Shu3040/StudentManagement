package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;


@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    List<Student> searchStudent();

    @Insert("INSERT INTO students(name, furigana, nickname, mailaddress, address, tel, age, gender, remark, is_deleted) " +
            "VALUES(#{name}, #{furigana}, #{nickname}, #{mailaddress}, #{address}, #{tel}, #{age}, #{gender}, #{remark}, #{isDeleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertStudent(Student student);

    @Select("SELECT * FROM students_courses")
    List<StudentCourse> searchStudentCourses();

}