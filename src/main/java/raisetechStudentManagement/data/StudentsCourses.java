package raisetechstudentmanagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentsCourses {
  private String id;
  private int studentId;
  private String course;
  private String courseStartDate;

  //映像修了予定日
  private String courseExpectedCompletionDate;
}
