package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;

/*
    受講生テーブルと受講生コーステーブルと紐づくRepositoryです
     */
@Mapper
public interface StudentRepository {
    /*
        受講生全件検索を行います
        @return 受講生一覧（全件）
         */
    @Select("SELECT * FROM students" )
    List<Student> search();
    /*
       受講生検索を行います
       @parm　id 受講生ID
       @return 受講生情報
     */
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(String id);

    /*
       受講生のコース情報の全件検索を行います
       @parm　id 受講生ID
       @return 受講生のコース情報（全件）
                */
    @Select("SELECT * FROM students_courses")
    List<StudentCourse> searchStudentCoursesList();

    /*
          受講生IDに紐づくコース情報の検索を行います
          @parm　studentId 受講生ID
          @return 受講生IDに紐づく受講生のコース情報
                   */
    @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
    List<StudentCourse> searchStudentCourse(String studentid);

    /*
             受講生を新規登録します
             IDに関しては自動採番を行う
             @parm　student　受講生
                      */
    @Insert("INSERT INTO students(name, furigana, nickname, mailaddress, address, tel, age, gender, remark, is_deleted) " +
            "VALUES(#{name}, #{furigana}, #{nickname}, #{mailaddress}, #{address}, #{tel}, #{age}, #{gender}, #{remark}, #{isDeleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);

    /*
                受講生コース情報を新規登録します
                @parm　studentCourse　受講生コース情報
                         */
    @Insert("INSERT INTO students_courses(student_id, course_name, start_date, end_date) " +
        "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    void registerStudentCourse(StudentCourse studentCourse);

    /*
               受講生詳細の更新を行います
               @parm　student　受講生
                        */
    @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickname}, mailaddress = #{mailaddress}," +
            " address = #{address}, tel = #{tel}, age = #{age}, gender = #{gender},remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(Student student);

    /*
     受講生コース情報のコース名を更新します
   　 @parm　studentCourse　受講生コース情報
     */
    @Update("UPDATE students_courses SET course_name = #{courseName} WHERE student_id = #{studentId} AND course_id = #{courseId}")
    void updateStudentCourse(StudentCourse studentCourse);

}