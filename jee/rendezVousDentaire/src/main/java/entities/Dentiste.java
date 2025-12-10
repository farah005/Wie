package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Dentiste")
public class Dentiste implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idD")
    private Integer idD;
    
    @Column(name = "nomD", length = 100, nullable = false)
    private String nomD;
    
    @Column(name = "prenomD", length = 100, nullable = false)
    private String prenomD;
    
    @Column(name = "emailD", length = 100, nullable = false, unique = true)
    private String emailD;
    
    @Column(name = "mdpD", length = 10)
    private String mdpD;
    
    @Column(name = "specialiteD", length = 100)
    private String specialiteD;
    
    @Column(name = "sexeD", length = 1)
    private String sexeD;
    
    @Column(name = "telD")
    private Integer telD;
    
    @Column(name = "photoD", length = 100)
    private String photoD;
    
    @OneToMany(mappedBy = "dentiste", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rendezvous> rendezvous;
    
    // Constructeurs
    public Dentiste() {}
    
    // Getters et Setters
    public Integer getIdD() { return idD; }
    public void setIdD(Integer idD) { this.idD = idD; }
    
    public String getNomD() { return nomD; }
    public void setNomD(String nomD) { this.nomD = nomD; }
    
    public String getPrenomD() { return prenomD; }
    public void setPrenomD(String prenomD) { this.prenomD = prenomD; }
    
    public String getEmailD() { return emailD; }
    public void setEmailD(String emailD) { this.emailD = emailD; }
    
    public String getMdpD() { return mdpD; }
    public void setMdpD(String mdpD) { this.mdpD = mdpD; }
    
    public String getSpecialiteD() { return specialiteD; }
    public void setSpecialiteD(String specialiteD) { this.specialiteD = specialiteD; }
    
    public String getSexeD() { return sexeD; }
    public void setSexeD(String sexeD) { this.sexeD = sexeD; }
    
    public Integer getTelD() { return telD; }
    public void setTelD(Integer telD) { this.telD = telD; }
    
    public String getPhotoD() { return photoD; }
    public void setPhotoD(String photoD) { this.photoD = photoD; }
    
    public List<Rendezvous> getRendezvous() { return rendezvous; }
    public void setRendezvous(List<Rendezvous> rendezvous) { this.rendezvous = rendezvous; }
}