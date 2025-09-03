package app.entities;


import app.enums.CourseName;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(nullable = false, name = "course_name")
    private CourseName courseName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @PreUpdate
    void preUpdate() {
        this.endDate = LocalDate.now();
    }

    @PrePersist
    void prePersist() {
        this.startDate = LocalDate.now();
    }

    // relationer til student:
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Student> students = new HashSet<>();



    // relationer til Teacher
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    // Liste af kurser


    // Hjælpe metoder
    public void addTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setCourse(this); // sync both sides
    }


}
