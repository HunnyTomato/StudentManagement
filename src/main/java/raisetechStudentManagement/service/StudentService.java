package raisetechstudentmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import raisetechstudentmanagement.data.Student;
import raisetechstudentmanagement.data.StudentsCourses;
import raisetechstudentmanagement.repository.StudentRepository;

@Service
public class StudentService {
  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/student")
  public List<Student> getStudentList(int age){
    return repository.searchByAge(age);
  }

  @GetMapping("/student")
  public List<Student> searchStudentList(int age){
    return repository.selectByAge(age);
  }

  @GetMapping("/students_Courses")
  public List<StudentsCourses> getStudentsCoursesList(String course) {
    return repository.searchByCourse(course);
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return repository.searchStudentsCourses();
  }
}
