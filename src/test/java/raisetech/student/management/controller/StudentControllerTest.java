package raisetech.student.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.student.management.data.Student;
import raisetech.student.management.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockmvc;

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できてからのリストが返ってくること() throws Exception{
    mockmvc.perform(get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の検索が実行できて返ってくること() throws Exception{
    String id = "777";
    mockmvc.perform(get("/student/{id}", id))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 受講生が返ってくること() throws Exception {
    mockmvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                  "student" : {
                    "name" : "津田晋太郎",
                    "hurigana" : "ツダシンタロウ",
                    "nickname" : "シン",
                    "mailAddress" : "test@gmail.com",
                    "area" : "千葉",
                    "age" : "25",
                    "gender" : "M",
                   "remark" : ""
                   },
                "studentCourseList" : [
                  {
                      "course" : "Java"
                  }
                 ]
                }
                """
        ))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());

  }

  @Test
  void 受講生を更新() throws Exception {
    mockmvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(
                """
                {
                  "student" : {
                    "id" : "23",
                    "name" : "津田晋太郎",
                    "hurigana" : "ツダシンタロウ",
                    "nickname" : "シン",
                    "mailAddress" : "test@gmail.com",
                    "area" : "千葉",
                    "age" : "25",
                    "gender" : "M",
                   "remark" : ""
                   },
                "studentCourseList" : [
                   {
                      "studentId" : "23",
                      "course" : "Java",
                      "coursesStartDate" : "2024-09-08T16:34:41.833614",
                      "coursesExpectedCompletionDate" : "2024-09-08T16:34:41.833614"
                   }
                  ]
                }
                """
            ))
        .andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any());

  }

  @Test
  void 受講生詳細の例外APIの実行ができてステータスが400で返ってくること() throws Exception{
    mockmvc.perform(get("/exception"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("このAPIは現在使われておりません。古いURLとなっております。"));
  }

  @Test
  void 受講生詳細の受講生で適切な値を用いたときに入力チェックに異常が発生しないこと(){
    Student student = new Student();
    student.setId("1");
    student.setName("津田晋太郎");
    student.setHurigana("ツダシンタロウ");
    student.setNickname("シン");
    student.setMailAddress("xxxxx@gamil.com");
    student.setArea("千葉");
    student.setGender("M");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックにかかること(){
    Student student = new Student();
    student.setId("テストです。");
    student.setName("津田晋太郎");
    student.setHurigana("ツダシンタロウ");
    student.setNickname("シン");
    student.setMailAddress("xxxxx@gamil.com");
    student.setArea("千葉");
    student.setGender("M");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");
  }

}
