package app.DAO;

import app.entities.Student;
import app.enums.CourseName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAO implements IDAO<Student, Long>{
    private final EntityManagerFactory emf;

    public StudentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public Student create(Student student) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return student;
        } catch(Exception ex){
            throw new RuntimeException("Error creating a student", ex);
        }
    }

    @Override
    public Student getById(Long id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Student.class, id);
        } catch(Exception ex){
            throw new RuntimeException("Error getting a student by Id", ex);
        }
    }

    @Override
    public Student update(Student student) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            return student;
        }catch(Exception ex){
            throw new RuntimeException("Error updating a student", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        try(EntityManager em = emf.createEntityManager()){
            Student student = em.find(Student.class, id);
            em.getTransaction().begin();
            em.remove(student);
            em.getTransaction().commit();
            return true;
        } catch(Exception ex){
            throw new RuntimeException("Error deleting a student", ex);
        }
    }

    @Override
    public List<Student> getAll() {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Student> quary = em.createQuery("SELECT s FROM Student s", Student.class);
            return quary.getResultList();
        } catch(Exception ex){
            throw new RuntimeException("Error getting all students", ex);
        }
    }

    public List<Student> getStudentsByCourseId(Long courseId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.course.id = :courseId", Student.class);
            query.setParameter("courseId", courseId);
            return query.getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching students for course ID: " + courseId, ex);
        }
    }

    public List<Student> getStudentsByTeacherId(Long teacherId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.course.teacher.id = :teacherId", Student.class);
            query.setParameter("teacherId", teacherId);
            return query.getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching students for teacher ID: " + teacherId, ex);
        }
    }




}
