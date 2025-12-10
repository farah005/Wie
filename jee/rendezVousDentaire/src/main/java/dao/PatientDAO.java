package dao;

import entities.Patient;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PatientDAO {
    
    @PersistenceContext(unitName = "cabinetDentaire")
    private EntityManager em;
    
    public void create(Patient patient) {
        em.persist(patient);
    }
    
    public Patient find(Integer id) {
        return em.find(Patient.class, id);
    }
    
    public Patient findByEmail(String email) {
        try {
            TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.emailP = :email", Patient.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Patient authenticate(String email, String password) {
        try {
            TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.emailP = :email AND p.mdpP = :password", 
                Patient.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Patient> findAll() {
        return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }
    
    public void update(Patient patient) {
        em.merge(patient);
    }
    
    public void delete(Integer id) {
        Patient patient = find(id);
        if (patient != null) {
            em.remove(patient);
        }
    }
}