package dao;
import entities.Dentiste;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DentisteDAO {
    
    @PersistenceContext(unitName = "cabinetDentaire")
    private EntityManager em;
    
    public void create(Dentiste dentiste) {
        em.persist(dentiste);
    }
    
    public Dentiste find(Integer id) {
        return em.find(Dentiste.class, id);
    }
    
    public Dentiste findByEmail(String email) {
        try {
            TypedQuery<Dentiste> query = em.createQuery(
                "SELECT d FROM Dentiste d WHERE d.emailD = :email", Dentiste.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Dentiste authenticate(String email, String password) {
        try {
            TypedQuery<Dentiste> query = em.createQuery(
                "SELECT d FROM Dentiste d WHERE d.emailD = :email AND d.mdpD = :password", 
                Dentiste.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Dentiste> findAll() {
        return em.createQuery("SELECT d FROM Dentiste d", Dentiste.class).getResultList();
    }
    
    public List<Dentiste> findBySpecialite(String specialite) {
        TypedQuery<Dentiste> query = em.createQuery(
            "SELECT d FROM Dentiste d WHERE d.specialiteD = :specialite", Dentiste.class);
        query.setParameter("specialite", specialite);
        return query.getResultList();
    }
    
    public void update(Dentiste dentiste) {
        em.merge(dentiste);
    }
    
    public void delete(Integer id) {
        Dentiste dentiste = find(id);
        if (dentiste != null) {
            em.remove(dentiste);
        }
    }
}