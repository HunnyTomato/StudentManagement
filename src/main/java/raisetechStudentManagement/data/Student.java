package raisetechstudentmanagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {
  private String id;
  private String name;
  private String hurigana;
  private String nickname;
  private String mailAddress;
  private String area;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;
}


