package raisetechstudentmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetechstudentmanagement.data.Student;
import raisetechstudentmanagement.data.StudentsCourses;
import raisetechstudentmanagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList(){
    return service.searchStudentList();
  }

  @GetMapping("/studentsCoursesList")
  public List<StudentsCourses> getStudentsCoursesList() {
    return service.searchStudentsCoursesList();
  }
}
