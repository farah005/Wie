package servlets;

import dao.PatientDAO;
import dao.DentisteDAO;
import dao.RendezvousDAO;
import dao.ServiceMedicalDAO;
import entities.Patient;
import entities.Dentiste;
import entities.Rendezvous;
import entities.ServiceMedical;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/publication")
public class PublicationServlet extends HttpServlet {
    
    @EJB
    private PatientDAO patientDAO;
    
    @EJB
    private DentisteDAO dentisteDAO;
    
    @EJB
    private RendezvousDAO rendezvousDAO;
    
    @EJB
    private ServiceMedicalDAO serviceDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "statistiques";
        }
        
        switch (action) {
            case "statistiques":
                afficherStatistiques(request, response);
                break;
            case "patients":
                listerPatients(request, response);
                break;
            case "dentistes":
                listerDentistes(request, response);
                break;
            case "rendezvous":
                listerRendezvous(request, response);
                break;
            case "services":
                listerServices(request, response);
                break;
            default:
                afficherStatistiques(request, response);
                break;
        }
    }
    
    private void afficherStatistiques(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Statistiques générales
        List<Patient> patients = patientDAO.findAll();
        List<Dentiste> dentistes = dentisteDAO.findAll();
        List<Rendezvous> rendezvous = rendezvousDAO.findAll();
        List<ServiceMedical> services = serviceDAO.findAll();
        
        // Compter les rendez-vous par statut
        long rdvEnAttente = rendezvous.stream()
            .filter(r -> "En attente".equals(r.getStatutRv()))
            .count();
        
        long rdvConfirmes = rendezvous.stream()
            .filter(r -> "Confirmé".equals(r.getStatutRv()))
            .count();
        
        long rdvTermines = rendezvous.stream()
            .filter(r -> "Terminé".equals(r.getStatutRv()))
            .count();
        
        long rdvAnnules = rendezvous.stream()
            .filter(r -> "Annulé".equals(r.getStatutRv()))
            .count();
        
        // Statistiques par sexe (patients)
        long patientsHommes = patients.stream()
            .filter(p -> "M".equals(p.getSexeP()))
            .count();
        
        long patientsFemmes = patients.stream()
            .filter(p -> "F".equals(p.getSexeP()))
            .count();
        
        // Statistiques par groupe sanguin
        long groupeA = patients.stream()
            .filter(p -> "A".equals(p.getGroupeSanguinP()))
            .count();
        
        long groupeB = patients.stream()
            .filter(p -> "B".equals(p.getGroupeSanguinP()))
            .count();
        
        long groupeO = patients.stream()
            .filter(p -> "O".equals(p.getGroupeSanguinP()))
            .count();
        
        long groupeAB = patients.stream()
            .filter(p -> "AB".equals(p.getGroupeSanguinP()))
            .count();
        
        // Attribuer les statistiques
        request.setAttribute("totalPatients", patients.size());
        request.setAttribute("totalDentistes", dentistes.size());
        request.setAttribute("totalRendezvous", rendezvous.size());
        request.setAttribute("totalServices", services.size());
        
        request.setAttribute("rdvEnAttente", rdvEnAttente);
        request.setAttribute("rdvConfirmes", rdvConfirmes);
        request.setAttribute("rdvTermines", rdvTermines);
        request.setAttribute("rdvAnnules", rdvAnnules);
        
        request.setAttribute("patientsHommes", patientsHommes);
        request.setAttribute("patientsFemmes", patientsFemmes);
        
        request.setAttribute("groupeA", groupeA);
        request.setAttribute("groupeB", groupeB);
        request.setAttribute("groupeO", groupeO);
        request.setAttribute("groupeAB", groupeAB);
        
        request.getRequestDispatcher("Publication.jsp").forward(request, response);
    }
    
    private void listerPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Patient> patients = patientDAO.findAll();
        request.setAttribute("patients", patients);
        request.setAttribute("action", "patients");
        request.getRequestDispatcher("Publication.jsp").forward(request, response);
    }
    
    private void listerDentistes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Dentiste> dentistes = dentisteDAO.findAll();
        request.setAttribute("dentistes", dentistes);
        request.setAttribute("action", "dentistes");
        request.getRequestDispatcher("Publication.jsp").forward(request, response);
    }
    
    private void listerRendezvous(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String statut = request.getParameter("statut");
        
        List<Rendezvous> rendezvous;
        if (statut != null && !statut.isEmpty()) {
            rendezvous = rendezvousDAO.findByStatut(statut);
        } else {
            rendezvous = rendezvousDAO.findAll();
        }
        
        request.setAttribute("rendezvous", rendezvous);
        request.setAttribute("action", "rendezvous");
        request.getRequestDispatcher("Publication.jsp").forward(request, response);
    }
    
    private void listerServices(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String type = request.getParameter("type");
        
        List<ServiceMedical> services;
        if (type != null && !type.isEmpty()) {
            services = serviceDAO.findByType(type);
        } else {
            services = serviceDAO.findAll();
        }
        
        request.setAttribute("services", services);
        request.setAttribute("action", "services");
        request.getRequestDispatcher("Publication.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("recherche".equals(action)) {
            rechercherPatients(request, response);
        } else {
            doGet(request, response);
        }
    }
    
    private void rechercherPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String critere = request.getParameter("critere");
        String valeur = request.getParameter("valeur");
        
        List<Patient> patients = patientDAO.findAll();
        
        if (critere != null && valeur != null && !valeur.isEmpty()) {
            switch (critere) {
                case "nom":
                    patients = patients.stream()
                        .filter(p -> p.getNomP().toLowerCase().contains(valeur.toLowerCase()))
                        .toList();
                    break;
                case "email":
                    patients = patients.stream()
                        .filter(p -> p.getEmailP().toLowerCase().contains(valeur.toLowerCase()))
                        .toList();
                    break;
                case "groupeSanguin":
                    patients = patients.stream()
                        .filter(p -> valeur.equals(p.getGroupeSanguinP()))
                        .toList();
                    break;
                case "sexe":
                    patients = patients.stream()
                        .filter(p -> valeur.equals(p.getSexeP()))
                        .toList();
                    break;
            }
        }
        
        request.setAttribute("patients", patients);
        request.setAttribute("action", "patients");
        request.getRequestDispatcher("Publication.jsp").forward(request, response);
    }
}