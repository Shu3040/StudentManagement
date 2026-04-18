package raisetech.student.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.repository.StudentRepository;

import java.util.List;


public class Application {

	@Autowired
	private StudentRepository repository;


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}





