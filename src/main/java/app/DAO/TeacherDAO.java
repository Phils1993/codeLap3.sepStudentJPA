package app.DAO;

import app.entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TeacherDAO implements IDAO<Teacher, Long>{

    private final EntityManagerFactory emf;

    public TeacherDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public Teacher create(Teacher teacher) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
            return teacher;
        }catch(RuntimeException e) {
            throw new RuntimeException("Cannot persist Teacher", e);
        }
    }

    @Override
    public Teacher getById(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            Teacher teacher = em.find(Teacher.class, id);
            return teacher;
        } catch(RuntimeException e) {
            throw new RuntimeException("Cannot find Teacher by Id", e);
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(teacher);
            em.getTransaction().commit();
            return teacher;
        } catch(RuntimeException e) {
            throw new RuntimeException("Cannot update Teacher", e);
        }
    }


    @Override
    public boolean delete(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            Teacher teacher = em.find(Teacher.class, id);
            em.getTransaction().begin();
            em.remove(teacher);
            em.getTransaction().commit();
            return true;
        } catch(RuntimeException e) {
            throw new RuntimeException("Cannot delete Teacher", e);
        }
    }

    @Override
    public List<Teacher> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
            return query.getResultList();
        } catch(RuntimeException e) {
            throw new RuntimeException("Cannot get All Teachers", e);
        }
    }
}
