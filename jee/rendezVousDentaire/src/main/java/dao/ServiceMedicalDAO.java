package dao;

import entities.ServiceMedical;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ServiceMedicalDAO {
    
    @PersistenceContext(unitName = "cabinetDentaire")
    private EntityManager em;
    
    public void create(ServiceMedical service) {
        em.persist(service);
    }
    
    public ServiceMedical find(Integer id) {
        return em.find(ServiceMedical.class, id);
    }
    
    public List<ServiceMedical> findAll() {
        return em.createQuery("SELECT s FROM ServiceMedical s", ServiceMedical.class)
                 .getResultList();
    }
    
    public List<ServiceMedical> findByType(String type) {
        return em.createQuery("SELECT s FROM ServiceMedical s WHERE s.typeSM = :type", 
                             ServiceMedical.class)
                 .setParameter("type", type)
                 .getResultList();
    }
    
    public void update(ServiceMedical service) {
        em.merge(service);
    }
    
    public void delete(Integer id) {
        ServiceMedical service = find(id);
        if (service != null) {
            em.remove(service);
        }
    }
}