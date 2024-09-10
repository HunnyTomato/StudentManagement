package raisetech.student.management.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

@Service
public class StudentService {
  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentsCourses = repository.searchStudentCourse(student.getId());
    return new StudentDetail(student, studentsCourses);
  }

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentsCourse -> {
      initStudentsCourse(studentsCourse, student.getId());
      repository.registerStudentCourse(studentsCourse);
    });
    return studentDetail;
  }

  void initStudentsCourse(StudentCourse studentsCourse, String id) {
    LocalDateTime now = LocalDateTime.now();
    studentsCourse.setStudentId(id);
    studentsCourse.setCoursesStartDate(now);
    studentsCourse.setCoursesExpectedCompletionDate(now.plusYears(1));
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail){
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList()
        .forEach(studentsCourse -> repository.updateStudentCourse(studentsCourse));
  }
}
