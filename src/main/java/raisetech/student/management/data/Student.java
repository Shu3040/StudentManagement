package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生情報")
@Getter
@Setter
public class Student {

    private String id;
    private String name;
    private String furigana;
    private String nickname;
    private String mailaddress;
    private String address;
    private String tel;
    @Min(15)
    @Max(70)
    private int age;
    private String gender;
    private String remark;
    private boolean isDeleted;


}


