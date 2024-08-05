package raisetechstudentmanagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetechstudentmanagement.data.Student;
import raisetechstudentmanagement.data.StudentsCourses;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;
}
