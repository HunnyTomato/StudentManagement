package raisetechstudentmanagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentsCourses {
  private int id;
  private String course;
  private String courseStartDate;

  //修了予定日
  private String courseExpectedCompletionDate;
}
