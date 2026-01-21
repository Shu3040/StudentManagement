package raisetech.student.managemant;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	private String name = "Yamada Shuto";
	private String age = "27";
	private String sex = "male";

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/studentInfo")
	public String getstudentInfo(){
		return name + " " + age + "歳" + " " + sex ;
	}

	@PostMapping ("/studentInfo")
	public void setstudentInfo (String name,String age,String sex){
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	@PostMapping("/studentName")
	public void  updateStudentName(String name){
		this.name = name;
	}
}
