package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ActeMedical")
public class ActeMedical implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAM")
    private Integer idAM;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRv", nullable = false)
    private Rendezvous rendezvous;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "numSM", nullable = false)
    private ServiceMedical serviceMedical;
    
    @Column(name = "descriptionAM", columnDefinition = "TEXT")
    private String descriptionAM;
    
    @Column(name = "tarifAM", precision = 6, scale = 2)
    private BigDecimal tarifAM;
    
    // Constructeurs
    public ActeMedical() {}
    
    // Getters et Setters
    public Integer getIdAM() { return idAM; }
    public void setIdAM(Integer idAM) { this.idAM = idAM; }
    
    public Rendezvous getRendezvous() { return rendezvous; }
    public void setRendezvous(Rendezvous rendezvous) { this.rendezvous = rendezvous; }
    
    public ServiceMedical getServiceMedical() { return serviceMedical; }
    public void setServiceMedical(ServiceMedical serviceMedical) { this.serviceMedical = serviceMedical; }
    
    public String getDescriptionAM() { return descriptionAM; }
    public void setDescriptionAM(String descriptionAM) { this.descriptionAM = descriptionAM; }
    
    public BigDecimal getTarifAM() { return tarifAM; }
    public void setTarifAM(BigDecimal tarifAM) { this.tarifAM = tarifAM; }
}