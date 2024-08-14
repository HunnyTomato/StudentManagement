package raisetechstudentmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import raisetechstudentmanagement.data.Student;
import raisetechstudentmanagement.data.StudentsCourses;
import raisetechstudentmanagement.domain.StudentDetail;
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

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return repository.searchStudentsCourses();
  }

  @Transactional
  public void registerStudent(StudentDetail student){
    repository.registerStudent(student.getStudent());
  }
}
