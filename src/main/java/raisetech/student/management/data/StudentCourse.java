package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {

  private String courseId;
  private String studentId;
  private String course;
  private LocalDateTime coursesStartDate;
  private LocalDateTime coursesExpectedCompletionDate;
}
