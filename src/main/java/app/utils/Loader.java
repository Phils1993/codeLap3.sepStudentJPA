package app.utils;

import app.DAO.CourseDAO;
import app.DAO.TeacherDAO;
import app.DAO.StudentDAO;
import app.entities.Course;
import app.entities.Teacher;
import app.entities.Student;
import app.enums.CourseName;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private final CourseDAO courseDAO;
    private final TeacherDAO teacherDAO;
    private final StudentDAO studentDAO;

    public Loader(EntityManagerFactory emf) {
        this.courseDAO = new CourseDAO(emf);
        this.teacherDAO = new TeacherDAO(emf);
        this.studentDAO = new StudentDAO(emf);
    }

    // Helper to create a teacher
    private Teacher newTeacher(String name, String email, String zoom) {
        return Teacher.builder()
                .name(name)
                .email(email)
                .zoom(zoom)
                .build();
    }

    // Helper to create a course
    private Course newCourse(CourseName name, String description, Teacher teacher) {
        Course course = Course.builder()
                .courseName(name)
                .description(description)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(6))
                .teacher(teacher)
                .build();

        if (teacher != null) {
            teacher.addCourse(course); // maintain bidirectional link
        }

        return course;
    }

    // Helper to create a student
    private Student newStudent(String name, String email, Course course) {
        Student student = Student.builder()
                .name(name)
                .email(email)
                .course(course)
                .build();

        if (course != null) {
            course.getStudents().add(student); // maintain bidirectional link
        }

        return student;
    }

    public void loadData() {
        // Teachers
        Teacher t1 = teacherDAO.create(newTeacher("Dr. Smith", "smith@university.com", "zoom1"));
        Teacher t2 = teacherDAO.create(newTeacher("Prof. Johnson", "johnson@university.com", "zoom2"));

        // Courses
        Course c1 = courseDAO.create(newCourse(
                CourseName.MATH,
                "Algebra and Geometry",
                t1
        ));

        Course c2 = courseDAO.create(newCourse(
                CourseName.SCIENCE,
                "Physics and Chemistry",
                t1
        ));

        Course c3 = courseDAO.create(newCourse(
                CourseName.HISTORY,
                "World History 101",
                t2
        ));

        Course c4 = courseDAO.create(newCourse(
                CourseName.ART,
                "Art & Literature",
                t2
        ));

        // Students
        studentDAO.create(newStudent("Alice", "alice@student.com", c1));
        studentDAO.create(newStudent("Bob", "bob@student.com", c1));
        studentDAO.create(newStudent("Hans", "hans@student.com", c2));
        studentDAO.create(newStudent("Charlie", "charlie@student.com", c2));
        studentDAO.create(newStudent("Diana", "diana@student.com", c3));
        studentDAO.create(newStudent("Eve", "eve@student.com", c4));
    }
}