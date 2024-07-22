package raisetechStudentManagement;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM StudentManagement WHERE name = #{name}")
  Student searchByName(String name);

  @Insert("INSERT student values(#{name}, #{age})")
  void registerStudent(String name, int age);



  @Delete("DELETE FROM student WHERE name = #{name}")
  void deleteStudent(String name);
}
