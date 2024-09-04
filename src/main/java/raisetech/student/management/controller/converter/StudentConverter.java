package raisetech.student.management.controller.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> studentList,
      List<StudentCourse> studentCourseList) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Student student : studentList){
      StudentDetail studentDetail =new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentCourseList = new ArrayList<>();
      for (StudentCourse studentCourse : studentCourseList){
        if (student.getId().equals(studentCourse.getStudentId())){
          convertStudentCourseList.add(studentCourse);
        }
      }
      studentDetail.setStudentsCourseList(convertStudentCourseList);
      studentDetails.add(studentDetail);
    }
    return studentDetails;
  }
}
