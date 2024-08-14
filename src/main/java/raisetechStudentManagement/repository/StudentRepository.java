package raisetechstudentmanagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetechstudentmanagement.data.Student;
import raisetechstudentmanagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCourses();

  @Select("SELECT * FROM students WHERE age = #{age}")
  List<Student> searchByAge(int age);

  @Insert("insert into students(name, hurigana, nickname, mailAddress, area, age, gender, remark,isDeleted) values(#{name},#{hurigana},#{nickname},#{mailAddress},#{area},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);
}
