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

import static app.services.Tasks.*;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        StudentDAO studentDAO = new StudentDAO(emf);
        CourseDAO courseDAO = new CourseDAO(emf);
        TeacherDAO teacherDAO = new TeacherDAO(emf);


        Loader loader = new Loader(emf);
        loader.loadData();


        updateCourseDescription(courseDAO, 1L, "Boksning");
        printStudentsByCourseId(studentDAO, 3L);
        printCourseByStudentId(courseDAO, 1L);
        printCoursesByTeacherId(courseDAO, 1L);
        printStudentsByTeacherId(studentDAO, 1L);

        emf.close();

    }
}


