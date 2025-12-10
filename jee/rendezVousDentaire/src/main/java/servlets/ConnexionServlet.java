package servlets;

import dao.PatientDAO;
import dao.DentisteDAO;
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

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
    
    @EJB
    private PatientDAO patientDAO;
    
    @EJB
    private DentisteDAO dentisteDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType"); // "patient" ou "dentiste"
        
        HttpSession session = request.getSession();
        
        if ("patient".equals(userType)) {
            Patient patient = patientDAO.authenticate(email, password);
            if (patient != null) {
                session.setAttribute("user", patient);
                session.setAttribute("userType", "patient");
                response.sendRedirect("Rendezvous.jsp");
            } else {
                request.setAttribute("error", "Email ou mot de passe incorrect");
                request.getRequestDispatcher("Connexion.jsp").forward(request, response);
            }
        } else if ("dentiste".equals(userType)) {
            Dentiste dentiste = dentisteDAO.authenticate(email, password);
            if (dentiste != null) {
                session.setAttribute("user", dentiste);
                session.setAttribute("userType", "dentiste");
                response.sendRedirect("AideSoignant.jsp");
            } else {
                request.setAttribute("error", "Email ou mot de passe incorrect");
                request.getRequestDispatcher("Connexion.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Type d'utilisateur invalide");
            request.getRequestDispatcher("Connexion.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Connexion.jsp").forward(request, response);
    }
}