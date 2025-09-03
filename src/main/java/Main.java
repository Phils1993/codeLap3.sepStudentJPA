import app.DAO.CourseDAO;
import app.DAO.StudentDAO;
import app.DAO.TeacherDAO;
import app.config.HibernateConfig;
import app.entities.Course;
import app.entities.Student;
import app.enums.CourseName;
import app.utils.Loader;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        StudentDAO studentDAO = new StudentDAO(emf);
        CourseDAO courseDAO = new CourseDAO(emf);
        TeacherDAO teacherDAO = new TeacherDAO(emf);


        Loader loader = new Loader(emf);
        loader.loadData();


        // update course description
        Course course = courseDAO.getById(1L);
        course.setDescription("New Course description - boksning");
        Course updatedCourse = courseDAO.update(course);
        System.out.println(updatedCourse.getDescription() + ": updated with new description");




        emf.close();

    }
}


