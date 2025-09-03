package app.DAO;

import app.entities.Course;
import app.entities.Student;
import app.entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CourseDAO implements IDAO<Course, Long> {
    private final EntityManagerFactory emf;

    public CourseDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }




    @Override
    public Course create(Course course) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            return course;
        } catch (Exception ex) {
            throw new RuntimeException("Error creating a new course", ex);
        }
    }

    @Override
    public Course getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Course.class, id);
        }catch (Exception ex){
            throw new RuntimeException("Error getting course by ID", ex);
        }
    }

    @Override
    public Course update(Course course) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(course);
            em.getTransaction().commit();
            return course;
        }catch (Exception ex){
            throw new RuntimeException("Error updating a new course", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Course course = em.find(Course.class, id);
            em.getTransaction().begin();
            if (course == null) {
                throw new RuntimeException("Course not found with ID: " + id);
            }
            em.remove(course);
            em.getTransaction().commit();
            return true;
        }catch (Exception ex){
            throw new RuntimeException("Error deleting a new course", ex);
        }
    }



    @Override
    public List<Course> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Course> query = em.createQuery("select c from Course c ",  Course.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Error getting all courses", ex);
        }
    }


    public Course findByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Course> query = em.createQuery(
                    "SELECT c FROM Course c WHERE c.name = :name", Course.class);
            query.setParameter("name", name);
            return query.getSingleResult(); // or getResultList() if expecting multiple
        } catch (Exception ex) {
            throw new RuntimeException("Error finding course by name", ex);
        }
    }


    public List<Course> getCoursesByStudentId(Long studentId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Course> query = em.createQuery(
                    "SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId", Course.class);
            query.setParameter("studentId", studentId);
            return query.getResultList();
        }
    }


    public List<Course> getCoursesByTeacherId(Long teacherId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Course> query = em.createQuery(
                    "SELECT c FROM Course c WHERE c.teacher.id = :teacherId", Course.class);
            query.setParameter("teacherId", teacherId);
            return query.getResultList();
        }
    }






}

