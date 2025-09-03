package app.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(nullable = false, unique = true, length = 100,name="email")
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;


}
