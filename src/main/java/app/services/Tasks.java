package app.services;

import app.DAO.CourseDAO;
import app.DAO.StudentDAO;
import app.entities.Course;
import app.entities.Student;

import java.util.List;

public class Tasks {

    public static void updateCourseDescription(CourseDAO courseDAO, Long courseId, String newDescription) {
        Course course = courseDAO.getById(courseId);
        if (course != null) {
            course.setDescription(newDescription);
            Course updatedCourse = courseDAO.update(course);
            System.out.println(updatedCourse.getDescription() + ": updated with new description");
        } else {
            System.out.println("⚠️ Course not found with ID: " + courseId);
        }
    }

    public static void printStudentsByCourseId(StudentDAO studentDAO, Long courseId) {
        List<Student> students = studentDAO.getStudentsByCourseId(courseId);
        System.out.println("👨‍🎓 Students enrolled in course ID " + courseId + ":");
        students.forEach(s -> System.out.println(" - " + s.getName()));
    }

    public static void printCourseByStudentId(CourseDAO courseDAO, Long studentId) {
        Course course = courseDAO.getCourseByStudentId(studentId);
        if (course != null) {
            System.out.println("Student is enrolled in: " + course.getCourseName());
        } else {
            System.out.println("⚠️ No course found for student ID: " + studentId);
        }
    }

    public static void printCoursesByTeacherId(CourseDAO courseDAO, Long teacherId) {
        List<Course> courses = courseDAO.getCoursesByTeacherId(teacherId);
        System.out.println("📚 Courses taught by teacher ID " + teacherId + ":");
        courses.forEach(c -> System.out.println(" - " + c.getCourseName() + ": " + c.getDescription()));
    }


    public static void printStudentsByTeacherId(StudentDAO studentDAO, Long teacherId) {
        List<Student> students = studentDAO.getStudentsByTeacherId(teacherId);
        System.out.println("👨‍🏫 Students taught by teacher ID " + teacherId + ":");
        students.forEach(s -> System.out.println(" - " + s.getName() + " (Course: " + s.getCourse().getCourseName() + ")"));
    }




}
