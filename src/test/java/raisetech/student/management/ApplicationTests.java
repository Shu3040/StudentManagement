package raisetech.student.management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@OpenAPIDefinition(info = @Info(title = "受講生管理システム"))
@SpringBootTest
class ApplicationTests {
	public static void main(String[] args){
		SpringApplication.run(Application.class,args);
	}

	@Test
	void contextLoads() {
	}

}
