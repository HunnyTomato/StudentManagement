package raisetech.student.management.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;

class StudentConverterTest {

  private StudentConverter sut;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
  }

  @Test
  void 受講生リストと受講生情報のリストから受講生詳細の作成() {
    Student student = new Student();
    student.setId("1");
    student.setName("津田晋太郎");
    student.setHurigana("ツダシンタロウ");
    student.setNickname("シン");
    student.setMailAddress("xxxxx@gamil.com");
    student.setArea("千葉");
    student.setGender("M");
    student.setRemark("");
    student.setDeleted(false);

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourse("Java");
    studentCourse.setCoursesStartDate(LocalDateTime.now());
    studentCourse.setCoursesExpectedCompletionDate(LocalDateTime.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
  }

  @Test
  void 受講生リストと受講生コースリストに紐づかないコース情報の除外() {
    Student student = new Student();
    student.setId("1");
    student.setName("津田晋太郎");
    student.setHurigana("ツダシンタロウ");
    student.setNickname("シン");
    student.setMailAddress("xxxxx@gamil.com");
    student.setArea("千葉");
    student.setGender("M");
    student.setRemark("");
    student.setDeleted(false);

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("1");
    studentCourse.setStudentId("2");
    studentCourse.setCourse("Java");
    studentCourse.setCoursesStartDate(LocalDateTime.now());
    studentCourse.setCoursesExpectedCompletionDate(LocalDateTime.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEmpty();
  }
}