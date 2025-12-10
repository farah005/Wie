package servlets;

import dao.ServiceMedicalDAO;
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

@WebServlet("/serviceMedical")
public class ServiceMedicalServlet extends HttpServlet {
    
    @EJB
    private ServiceMedicalDAO serviceDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("liste".equals(action)) {
            List<ServiceMedical> services = serviceDAO.findAll();
            request.setAttribute("services", services);
            request.getRequestDispatcher("Service.jsp").forward(request, response);
        } else if ("details".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                Integer id = Integer.parseInt(idStr);
                ServiceMedical service = serviceDAO.find(id);
                request.setAttribute("service", service);
                request.getRequestDispatcher("Service.jsp").forward(request, response);
            }
        } else {
            List<ServiceMedical> services = serviceDAO.findAll();
            request.setAttribute("services", services);
            request.getRequestDispatcher("Service.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String nom = request.getParameter("nom");
            String type = request.getParameter("type");
            String description = request.getParameter("description");
            String tarifStr = request.getParameter("tarif");
            
            ServiceMedical service = new ServiceMedical();
            service.setNomSM(nom);
            service.setTypeSM(type);
            service.setDescriptionSM(description);
            
            if (tarifStr != null && !tarifStr.isEmpty()) {
                service.setTarifSM(new BigDecimal(tarifStr));
            }
            
            serviceDAO.create(service);
            
            response.sendRedirect("serviceMedical?action=liste");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la création du service");
            request.getRequestDispatcher("Service.jsp").forward(request, response);
        }
    }
}