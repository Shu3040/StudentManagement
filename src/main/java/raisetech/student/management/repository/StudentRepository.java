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
    List<Student> search();
    /*
    受講生検索を行います
    @parm　id 受講生ID
    @return 受講生情報
     */
    Student searchStudent(String id);

    /*
    受講生のコース情報の全件検索を行います
    @parm　id 受講生ID
    @return 受講生のコース情報（全件）
     */
    List<StudentCourse> searchStudentCoursesList();

    /*
    受講生IDに紐づくコース情報の検索を行います
    @parm　studentId 受講生ID
    @return 受講生IDに紐づく受講生のコース情報
     */
    List<StudentCourse> searchStudentCourse(String studentid);

    /*
    受講生を新規登録します
    IDに関しては自動採番を行う
    @parm　student　受講生
     */
    void registerStudent(Student student);

    /*
    受講生コース情報を新規登録します
    @parm　studentCourse　受講生コース情報
     */
    void registerStudentCourse(StudentCourse studentCourse);

    /*
    受講生詳細の更新を行います
    @parm　student　受講生
     */
    void updateStudent(Student student);

    /*
    受講生コース情報のコース名を更新します
    @parm　studentCourse　受講生コース情報
     */
    void updateStudentCourse(StudentCourse studentCourse);

}