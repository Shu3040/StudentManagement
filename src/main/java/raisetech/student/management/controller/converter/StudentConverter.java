package raisetech.student.management.controller.converter;

import org.springframework.stereotype.Component;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
受講生詳細を受講生や受講生コース情報、もしくはその逆の変換を行うコンバーターです。
 */

@Component
public class StudentConverter {

 /*
 受講生に紐づく受講生コース情報をマッピングする。
 受講生コース情報は樹子政に対して複数存在するのでループを回して受講生詳細情報を組み立てる。
 @parm　students　受講生一覧
 @parm　studentCourses 受講生コース情報のリスト
 @return 受講生詳細壌情報のリスト
  */
    public List<StudentDetail> convertStudentDetails(List<Student> studentList, List<StudentCourse> studentCourseList) {
        List<StudentDetail> studentDetails = new ArrayList<>();
        studentList.forEach(student -> {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);

            List<StudentCourse> convertStudentCoursesList = studentCourseList.stream()
                    .filter(sc -> Objects.equals(sc.getStudentId(), student.getId()))
                    //.filter(studentCourse -> student.getId().equals(studentCourse.getStudentId()))
                    .collect(Collectors.toList());

            studentDetail.setStudentCourseList(convertStudentCoursesList);

            studentDetails.add(studentDetail);
        });
        return studentDetails;
    }

}
