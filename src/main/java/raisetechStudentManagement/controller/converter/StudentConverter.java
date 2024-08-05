package raisetechstudentmanagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import raisetechstudentmanagement.data.Student;
import raisetechstudentmanagement.data.StudentsCourses;
import raisetechstudentmanagement.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentsDetails(List<Student> students,
      List<StudentsCourses> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Student student : students){
      StudentDetail studentDetail =new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses> convertStudentCourses = new ArrayList<>();
      for (StudentsCourses studentCourse : studentsCourses){
        if (student.getId().equals(studentCourse.getStudentId())){
          convertStudentCourses.add(studentCourse);
        }
      }
      studentDetail.setStudentsCourses(convertStudentCourses);
      studentDetails.add(studentDetail);
    }
    return studentDetails;
  }
}
