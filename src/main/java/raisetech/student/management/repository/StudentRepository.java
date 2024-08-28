package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

@Mapper
public interface StudentRepository {

  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentCourseList();

  @Select("SELECT * FROM students where id = #{id}")
  Student searchStudent(String id);

  @Select("SELECT * FROM students_course"
      + "ses where studentId = #{studentId}")
  List<StudentCourse> searchStudentCourse(String studentId);

  void registerStudent(Student student);

  void registerStudentCourse(StudentCourse studentCourse);

  void updateStudent(Student student);

  void updateStudentCourse(StudentCourse studentsCourses);
}
