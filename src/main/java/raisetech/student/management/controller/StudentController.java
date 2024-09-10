package raisetech.student.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.exception.TestException;
import raisetech.student.management.service.StudentService;

@Validated
@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList(){
    return service.searchStudentList();
  }

  @Operation(summary = "受講生検索", description = "受講生を検索します。")
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id){
    return service.searchStudent(id);
  }

  @Operation(summary = "受講生登録", description = "受講生を登録します。")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail){
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  @Operation(summary = "受講生更新", description = "受講生を更新します。")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail){
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("受講生を更新します。");
  }

  @GetMapping("/exception")
  public ResponseEntity<String> throwException() throws NotFoundException {
    throw new NotFoundException("このAPIは現在使われておりません。古いURLとなっております。");
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
