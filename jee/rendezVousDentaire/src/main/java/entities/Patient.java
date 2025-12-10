package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Patient")
public class Patient implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idP")
    private Integer idP;
    
    @Column(name = "nomP", length = 100, nullable = false)
    private String nomP;
    
    @Column(name = "prenomP", length = 100, nullable = false)
    private String prenomP;
    
    @Column(name = "emailP", length = 100, nullable = false, unique = true)
    private String emailP;
    
    @Column(name = "groupeSanguinP", length = 2)
    private String groupeSanguinP;
    
    @Column(name = "photoP", length = 100)
    private String photoP;
    
    @Column(name = "sexeP", length = 1)
    private String sexeP;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateNP")
    private Date dateNP;
    
    @Column(name = "recouvrementP", length = 100)
    private String recouvrementP;
    
    @Column(name = "mdpP", length = 10)
    private String mdpP;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rendezvous> rendezvous;
    
    // Constructeurs
    public Patient() {}
    
    public Patient(String nomP, String prenomP, String emailP) {
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.emailP = emailP;
    }
    
    // Getters et Setters
    public Integer getIdP() { return idP; }
    public void setIdP(Integer idP) { this.idP = idP; }
    
    public String getNomP() { return nomP; }
    public void setNomP(String nomP) { this.nomP = nomP; }
    
    public String getPrenomP() { return prenomP; }
    public void setPrenomP(String prenomP) { this.prenomP = prenomP; }
    
    public String getEmailP() { return emailP; }
    public void setEmailP(String emailP) { this.emailP = emailP; }
    
    public String getGroupeSanguinP() { return groupeSanguinP; }
    public void setGroupeSanguinP(String groupeSanguinP) { this.groupeSanguinP = groupeSanguinP; }
    
    public String getPhotoP() { return photoP; }
    public void setPhotoP(String photoP) { this.photoP = photoP; }
    
    public String getSexeP() { return sexeP; }
    public void setSexeP(String sexeP) { this.sexeP = sexeP; }
    
    public Date getDateNP() { return dateNP; }
    public void setDateNP(Date dateNP) { this.dateNP = dateNP; }
    
    public String getRecouvrementP() { return recouvrementP; }
    public void setRecouvrementP(String recouvrementP) { this.recouvrementP = recouvrementP; }
    
    public String getMdpP() { return mdpP; }
    public void setMdpP(String mdpP) { this.mdpP = mdpP; }
    
    public List<Rendezvous> getRendezvous() { return rendezvous; }
    public void setRendezvous(List<Rendezvous> rendezvous) { this.rendezvous = rendezvous; }
}