package servlets;

import dao.RendezvousDAO;
import dao.PatientDAO;
import dao.DentisteDAO;
import entities.Rendezvous;
import entities.Patient;
import entities.Dentiste;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/rendezvous")
public class RendezvousServlet extends HttpServlet {
    
    @EJB
    private RendezvousDAO rendezvousDAO;
    
    @EJB
    private PatientDAO patientDAO;
    
    @EJB
    private DentisteDAO dentisteDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");
        
        if ("patient".equals(userType)) {
            Patient patient = (Patient) session.getAttribute("user");
            List<Rendezvous> rendezvous = rendezvousDAO.findByPatient(patient.getIdP());
            request.setAttribute("rendezvous", rendezvous);
        } else if ("dentiste".equals(userType)) {
            Dentiste dentiste = (Dentiste) session.getAttribute("user");
            List<Rendezvous> rendezvous = rendezvousDAO.findByDentiste(dentiste.getIdD());
            request.setAttribute("rendezvous", rendezvous);
        }
        
        // Liste des dentistes pour le formulaire
        List<Dentiste> dentistes = dentisteDAO.findAll();
        request.setAttribute("dentistes", dentistes);
        
        request.getRequestDispatcher("Rendezvous.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            HttpSession session = request.getSession();
            Patient patient = (Patient) session.getAttribute("user");
            
            if (patient == null) {
                response.sendRedirect("connexion");
                return;
            }
            
            String idDentisteStr = request.getParameter("idDentiste");
            String dateRdvStr = request.getParameter("dateRdv");
            String heureRdvStr = request.getParameter("heureRdv");
            String details = request.getParameter("details");
            
            Rendezvous rdv = new Rendezvous();
            rdv.setPatient(patient);
            
            Integer idDentiste = Integer.parseInt(idDentisteStr);
            Dentiste dentiste = dentisteDAO.find(idDentiste);
            rdv.setDentiste(dentiste);
            
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRdv = sdfDate.parse(dateRdvStr);
            rdv.setDateRv(dateRdv);
            
            SimpleDateFormat sdfHeure = new SimpleDateFormat("HH:mm");
            Date heureRdv = sdfHeure.parse(heureRdvStr);
            rdv.setHeureRv(heureRdv);
            
            rdv.setStatutRv("En attente");
            rdv.setDetailsRv(details);
            
            rendezvousDAO.create(rdv);
            
            response.sendRedirect("rendezvous");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la création du rendez-vous");
            doGet(request, response);
        }
    }
}