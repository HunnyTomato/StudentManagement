package raisetechstudentmanagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
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

  @Select("SELECT * FROM students WHERE age = <=30, >=39")
  List<Student> selectByAge(int age);

  @Select("SELECT * FROM students WHERE course = Java")
  List<StudentsCourses> searchByCourse(String course);
}
