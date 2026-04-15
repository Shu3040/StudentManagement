package raisetech.student.management.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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