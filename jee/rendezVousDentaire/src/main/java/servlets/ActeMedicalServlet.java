package servlets;

import dao.ActeMedicalDAO;
import dao.RendezvousDAO;
import dao.ServiceMedicalDAO;
import entities.ActeMedical;
import entities.Rendezvous;
import entities.ServiceMedical;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/acteMedical")
public class ActeMedicalServlet extends HttpServlet {
    
    @EJB
    private ActeMedicalDAO acteDAO;
    
    @EJB
    private RendezvousDAO rendezvousDAO;
    
    @EJB
    private ServiceMedicalDAO serviceDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idRdvStr = request.getParameter("idRdv");
        
        if (idRdvStr != null) {
            Integer idRdv = Integer.parseInt(idRdvStr);
            List<ActeMedical> actes = acteDAO.findByRendezvous(idRdv);
            Rendezvous rdv = rendezvousDAO.find(idRdv);
            
            request.setAttribute("actes", actes);
            request.setAttribute("rendezvous", rdv);
        }
        
        List<ServiceMedical> services = serviceDAO.findAll();
        request.setAttribute("services", services);
        
        request.getRequestDispatcher("ActeMedical.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String idRdvStr = request.getParameter("idRdv");
            String numSMStr = request.getParameter("numSM");
            String description = request.getParameter("description");
            String tarifStr = request.getParameter("tarif");
            
            Integer idRdv = Integer.parseInt(idRdvStr);
            Integer numSM = Integer.parseInt(numSMStr);
            
            Rendezvous rdv = rendezvousDAO.find(idRdv);
            ServiceMedical service = serviceDAO.find(numSM);
            
            ActeMedical acte = new ActeMedical();
            acte.setRendezvous(rdv);
            acte.setServiceMedical(service);
            acte.setDescriptionAM(description);
            
            if (tarifStr != null && !tarifStr.isEmpty()) {
                acte.setTarifAM(new BigDecimal(tarifStr));
            } else {
                acte.setTarifAM(service.getTarifSM());
            }
            
            acteDAO.create(acte);
            
            response.sendRedirect("acteMedical?idRdv=" + idRdv);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la création de l'acte médical");
            doGet(request, response);
        }
    }
}