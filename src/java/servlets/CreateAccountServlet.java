package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import services.RoleService;
import services.UserService;

/**
 *
 * @author OS
 */
public class CreateAccountServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = "";
        String message2 = "Account successfully created, go back to <a href='login'>log in page</a>";
        session.setAttribute("message", message);
        
        String created = (String) session.getAttribute("created");
        if (created == "true") {
            session.setAttribute("message", message2);
            session.setAttribute("created", "");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/create.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            UserService userService = new UserService();
            RoleService roleService = new RoleService();
            List<Role> roles = null;
            
            try {
                roles = roleService.getAll();
            }catch (Exception ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String email = request.getParameter("addEmail");
            String firstName = request.getParameter("addFirst");
            String lastName = request.getParameter("addLast");
            String password = request.getParameter("addPassword");  
            Role role = roles.get(1); //user can only create user account
            try {
                userService.insert(email, true, firstName, lastName, password, role);
            }catch (Exception ex) {
                Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpSession session = request.getSession();
            session.setAttribute("created", "true");
            
            response.sendRedirect("create");
    }

}
