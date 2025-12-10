package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Rendezvous")
public class Rendezvous implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRv")
    private Integer idRv;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idP", nullable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idD", nullable = false)
    private Dentiste dentiste;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateRv", nullable = false)
    private Date dateRv;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "heureRv", nullable = false)
    private Date heureRv;
    
    @Column(name = "statutRv", length = 100, nullable = false)
    private String statutRv;
    
    @Column(name = "detailsRv", columnDefinition = "TEXT")
    private String detailsRv;
    
    @OneToMany(mappedBy = "rendezvous", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ActeMedical> actesMedicaux;
    
    // Constructeurs
    public Rendezvous() {}
    
    // Getters et Setters
    public Integer getIdRv() { return idRv; }
    public void setIdRv(Integer idRv) { this.idRv = idRv; }
    
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    
    public Dentiste getDentiste() { return dentiste; }
    public void setDentiste(Dentiste dentiste) { this.dentiste = dentiste; }
    
    public Date getDateRv() { return dateRv; }
    public void setDateRv(Date dateRv) { this.dateRv = dateRv; }
    
    public Date getHeureRv() { return heureRv; }
    public void setHeureRv(Date heureRv) { this.heureRv = heureRv; }
    
    public String getStatutRv() { return statutRv; }
    public void setStatutRv(String statutRv) { this.statutRv = statutRv; }
    
    public String getDetailsRv() { return detailsRv; }
    public void setDetailsRv(String detailsRv) { this.detailsRv = detailsRv; }
    
    public List<ActeMedical> getActesMedicaux() { return actesMedicaux; }
    public void setActesMedicaux(List<ActeMedical> actesMedicaux) { this.actesMedicaux = actesMedicaux; }
}