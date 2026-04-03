package raisetech.student.managemant;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class StudentCourse {

    private  String courseId;
    private  String id;
    private  String courseName;
    private  LocalDateTime startDate;
    private  LocalDateTime endDate;
}
