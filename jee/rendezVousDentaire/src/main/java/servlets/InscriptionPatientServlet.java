package servlets;

import dao.PatientDAO;
import entities.Patient;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/inscriptionPatient")
public class InscriptionPatientServlet extends HttpServlet {
    
    @EJB
    private PatientDAO patientDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Récupération des paramètres
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            String sexe = request.getParameter("sexe");
            String groupeSanguin = request.getParameter("groupeSanguin");
            String dateNaissanceStr = request.getParameter("dateNaissance");
            String recouvrement = request.getParameter("recouvrement");
            
            // Vérifier si l'email existe déjà
            if (patientDAO.findByEmail(email) != null) {
                request.setAttribute("error", "Cet email est déjà utilisé");
                request.getRequestDispatcher("Patient.jsp").forward(request, response);
                return;
            }
            
            // Créer le patient
            Patient patient = new Patient();
            patient.setNomP(nom);
            patient.setPrenomP(prenom);
            patient.setEmailP(email);
            patient.setMdpP(motDePasse);
            patient.setSexeP(sexe);
            patient.setGroupeSanguinP(groupeSanguin);
            patient.setRecouvrementP(recouvrement);
            
            // Conversion de la date
            if (dateNaissanceStr != null && !dateNaissanceStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateNaissance = sdf.parse(dateNaissanceStr);
                patient.setDateNP(dateNaissance);
            }
            
            // Sauvegarder le patient
            patientDAO.create(patient);
            
            // Redirection vers la page de confirmation
            response.sendRedirect("validerInscription.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'inscription: " + e.getMessage());
            request.getRequestDispatcher("Patient.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Patient.jsp").forward(request, response);
    }
}