package dao;

import entities.ActeMedical;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ActeMedicalDAO {
    
    @PersistenceContext(unitName = "cabinetDentaire")
    private EntityManager em;
    
    public void create(ActeMedical acte) {
        em.persist(acte);
    }
    
    public ActeMedical find(Integer id) {
        return em.find(ActeMedical.class, id);
    }
    
    public List<ActeMedical> findAll() {
        return em.createQuery("SELECT a FROM ActeMedical a", ActeMedical.class)
                 .getResultList();
    }
    
    public List<ActeMedical> findByRendezvous(Integer idRdv) {
        return em.createQuery("SELECT a FROM ActeMedical a WHERE a.rendezvous.idRv = :idRv", 
                             ActeMedical.class)
                 .setParameter("idRv", idRdv)
                 .getResultList();
    }
    
    public List<ActeMedical> findByService(Integer numSM) {
        return em.createQuery("SELECT a FROM ActeMedical a WHERE a.serviceMedical.numSM = :numSM", 
                             ActeMedical.class)
                 .setParameter("numSM", numSM)
                 .getResultList();
    }
    
    public void update(ActeMedical acte) {
        em.merge(acte);
    }
    
    public void delete(Integer id) {
        ActeMedical acte = find(id);
        if (acte != null) {
            em.remove(acte);
        }
    }
}