package app.entities;


import app.enums.CourseName;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name="course_name")
    private CourseName courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false,name="end_date")
    private LocalDate endDate;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;



}
