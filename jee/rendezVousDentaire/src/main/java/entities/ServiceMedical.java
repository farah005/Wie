package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ServiceMedical")
public class ServiceMedical implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numSM")
    private Integer numSM;
    
    @Column(name = "nomSM", length = 100, nullable = false)
    private String nomSM;
    
    @Column(name = "typeSM", length = 100, nullable = false)
    private String typeSM;
    
    @Column(name = "descriptionSM", columnDefinition = "TEXT")
    private String descriptionSM;
    
    @Column(name = "tarifSM", precision = 6, scale = 2)
    private BigDecimal tarifSM;
    
    @OneToMany(mappedBy = "serviceMedical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ActeMedical> actesMedicaux;
    
    // Constructeurs
    public ServiceMedical() {}
    
    // Getters et Setters
    public Integer getNumSM() { return numSM; }
    public void setNumSM(Integer numSM) { this.numSM = numSM; }
    
    public String getNomSM() { return nomSM; }
    public void setNomSM(String nomSM) { this.nomSM = nomSM; }
    
    public String getTypeSM() { return typeSM; }
    public void setTypeSM(String typeSM) { this.typeSM = typeSM; }
    
    public String getDescriptionSM() { return descriptionSM; }
    public void setDescriptionSM(String descriptionSM) { this.descriptionSM = descriptionSM; }
    
    public BigDecimal getTarifSM() { return tarifSM; }
    public void setTarifSM(BigDecimal tarifSM) { this.tarifSM = tarifSM; }
    
    public List<ActeMedical> getActesMedicaux() { return actesMedicaux; }
    public void setActesMedicaux(List<ActeMedical> actesMedicaux) { this.actesMedicaux = actesMedicaux; }
}