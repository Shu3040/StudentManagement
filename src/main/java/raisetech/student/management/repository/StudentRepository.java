package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;


@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students" )
    List<Student> search();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(String id);


    @Select("SELECT * FROM students_courses")
    List<StudentCourse> searchStudentCoursesList();

    @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
    List<StudentCourse> searchStudentsCourses(String studentid);


    @Insert("INSERT INTO students(name, furigana, nickname, mailaddress, address, tel, age, gender, remark, is_deleted) " +
            "VALUES(#{name}, #{furigana}, #{nickname}, #{mailaddress}, #{address}, #{tel}, #{age}, #{gender}, #{remark}, #{isDeleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);

    @Insert("INSERT INTO students_courses(student_id, course_name, start_date, end_date) " +
        "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    void registerStudentCourse(StudentCourse studentCourse);

    @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickname}, mailaddress = #{mailaddress}," +
            " address = #{address}, tel = #{tel}, age = #{age}, gender = #{gender},remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(Student student);


    @Update("UPDATE students_courses SET course_name = #{courseName} WHERE student_id = #{studentId} AND course_id = #{courseId}")
    void updateStudentCourse(StudentCourse studentCourse);
    /*@Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
    void updateStudentCourse(StudentCourse studentCourse);*/

    @Select("SELECT * FROM students_courses")
    List<StudentCourse> searchStudentCourses();

}