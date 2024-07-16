package raisetechStudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "yamamoto daisuke";
	private String age = "25";

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/student")
	public String getstudent(){
		return name + " " + age + "歳";
	}

	@PostMapping("/student")
	public void setstudent(String name, String age){
		this.name = name;
		this.age = age;
	}
}
