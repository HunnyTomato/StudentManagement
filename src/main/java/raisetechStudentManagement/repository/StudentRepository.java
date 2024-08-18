package raisetechstudentmanagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetechstudentmanagement.data.Student;
import raisetechstudentmanagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  @Select("SELECT * FROM students where id = #{id}")
  Student searchStudent(String id);

  @Select("SELECT * FROM students_courses where studentId = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);

  @Insert("insert into students(name, hurigana, nickname, mailAddress, area, age, gender, remark,isDeleted) values(#{name},#{hurigana},#{nickname},#{mailAddress},#{area},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("insert into students_courses(studentId, course, coursesStartDate, coursesExpectedCompletionDate)"
      + "values(#{studentId}, #{course}, #{coursesStartDate}, #{coursesExpectedCompletionDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("update students set name = #{name}, hurigana = #{hurigana}, nickname = #{nickname},"
      + " mailAddress = #{mailAddress}, area = #{area}, age = #{age}, gender = #{gender},"
      + " remark = #{remark}, isDeleted = #{isDeleted} where id = #{id}")
  void updateStudent(Student student);

  @Update("update students_courses set course = #{course} where studentId = #{studentId}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
