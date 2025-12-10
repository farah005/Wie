package servlets;

import dao.DentisteDAO;
import entities.Dentiste;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listeDentistes")
public class DentisteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private DentisteDAO dentisteDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Dentiste> dentistes = dentisteDAO.findAll();
        request.setAttribute("dentistes", dentistes);
        request.getRequestDispatcher("AideSoignant.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}