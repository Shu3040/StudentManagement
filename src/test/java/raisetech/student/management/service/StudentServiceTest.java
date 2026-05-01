package raisetech.student.management.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;
    @Mock
    private StudentConverter converter;

    private StudentService sut;

    @BeforeEach
    void before(){
        sut = new  StudentService(repository,converter);
    }

    @Test
    void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること(){
        //事前準備
        StudentService sut = new StudentService(repository,converter);
        //List<StudentDetail> expected = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();
        Mockito.when(repository.search()).thenReturn(studentList);
        Mockito.when(repository.searchStudentCoursesList()).thenReturn(studentCourseList);

        //実行
        List<StudentDetail> actual = sut.searchStudentList();

        //検証
        //Assertions.assertEquals(expected,actual);
        Mockito.verify(repository,Mockito.times(1)).search();
        Mockito.verify(repository,Mockito.times(1)).searchStudentCoursesList();
        Mockito.verify(converter,Mockito.times(1)).convertStudentDetails(studentList,studentCourseList);
    }

    @Test
    void 受講生詳細の検索(){
        String id = "test-id";
        Student student = new Student();
        student.setId(id);
        List<StudentCourse> studentCourseList = new ArrayList<>();
        Mockito.when(repository.searchStudent(id)).thenReturn(student);
        Mockito.when(repository.searchStudentCourse(id)).thenReturn(studentCourseList);

        StudentDetail actual = sut.searchStudent(id);

        Mockito.verify(repository, Mockito.times(1)).searchStudent(id);
        Mockito.verify(repository, Mockito.times(1)).searchStudentCourse(id);
        Assertions.assertEquals(student, actual.getStudent());
        Assertions.assertEquals(studentCourseList, actual.getStudentCourseList());
    }

    @Test
    void 受講生コース情報の全件検索(){
        List<StudentCourse> expected = new ArrayList<>();
        Mockito.when(repository.searchStudentCoursesList()).thenReturn(expected);

        List<StudentCourse> actual = sut.searchStudentCourseList();

        Mockito.verify(repository, Mockito.times(1)).searchStudentCoursesList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void 受講生詳細の登録(){
        Student student = new Student();
        student.setId("test-id");
        StudentCourse course1 = new StudentCourse();
        StudentCourse course2 = new StudentCourse();
        List<StudentCourse> courseList = List.of(course1, course2);
        StudentDetail studentDetail = new StudentDetail(student, courseList);

        StudentDetail actual = sut.registerStudent(studentDetail);

        Mockito.verify(repository, Mockito.times(1)).registerStudent(student);
        Mockito.verify(repository, Mockito.times(1)).registerStudentCourse(course1);
        Mockito.verify(repository, Mockito.times(1)).registerStudentCourse(course2);

        Assertions.assertEquals(student.getId(), course1.getStudentId());
        Assertions.assertEquals(student.getId(), course2.getStudentId());
        Assertions.assertEquals(studentDetail, actual);
    }

    @Test
    void 受講生詳細の更新(){
        Student student = new Student();
        student.setId("test-id");
        StudentCourse course1 = new StudentCourse();
        StudentCourse course2 = new StudentCourse();
        List<StudentCourse> courseList = List.of(course1, course2);
        StudentDetail studentDetail = new StudentDetail(student, courseList);

        sut.updateStudent(studentDetail);

        Mockito.verify(repository, Mockito.times(1)).updateStudent(student);
        Mockito.verify(repository, Mockito.times(1)).updateStudentCourse(course1);
        Mockito.verify(repository, Mockito.times(1)).updateStudentCourse(course2);
    }

    }
