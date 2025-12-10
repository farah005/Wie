package dao;

import entities.Rendezvous;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class RendezvousDAO {
    
    @PersistenceContext(unitName = "cabinetDentaire")
    private EntityManager em;
    
    public void create(Rendezvous rdv) {
        em.persist(rdv);
    }
    
    public Rendezvous find(Integer id) {
        return em.find(Rendezvous.class, id);
    }
    
    public List<Rendezvous> findAll() {
        return em.createQuery("SELECT r FROM Rendezvous r", Rendezvous.class)
                 .getResultList();
    }
    
    public List<Rendezvous> findByPatient(Integer idPatient) {
        return em.createQuery("SELECT r FROM Rendezvous r WHERE r.patient.idP = :idP", 
                             Rendezvous.class)
                 .setParameter("idP", idPatient)
                 .getResultList();
    }
    
    public List<Rendezvous> findByDentiste(Integer idDentiste) {
        return em.createQuery("SELECT r FROM Rendezvous r WHERE r.dentiste.idD = :idD", 
                             Rendezvous.class)
                 .setParameter("idD", idDentiste)
                 .getResultList();
    }
    
    public List<Rendezvous> findByDate(Date date) {
        return em.createQuery("SELECT r FROM Rendezvous r WHERE r.dateRv = :date", 
                             Rendezvous.class)
                 .setParameter("date", date)
                 .getResultList();
    }
    
    public List<Rendezvous> findByStatut(String statut) {
        return em.createQuery("SELECT r FROM Rendezvous r WHERE r.statutRv = :statut", 
                             Rendezvous.class)
                 .setParameter("statut", statut)
                 .getResultList();
    }
    
    public void update(Rendezvous rdv) {
        em.merge(rdv);
    }
    
    public void delete(Integer id) {
        Rendezvous rdv = find(id);
        if (rdv != null) {
            em.remove(rdv);
        }
    }
}