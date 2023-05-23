/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import models.Item;
import models.User;
import services.ItemService;
import services.UserService;
import filters.AuthenticationFilter;
import models.Category;
import models.Role;
import services.CategoryService;
import services.RoleService;

/**
 *
 * @author OS
 */
public class AdminServlet extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        ItemService itemService = new ItemService();
        CategoryService categoryService = new CategoryService();
        
        
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            List<User> users = userService.getAll();
            
            List<Category> categories = categoryService.getAll();
            List<Role> roles = roleService.getAll();
            
            request.setAttribute("users",users);
            request.setAttribute("roles",roles);
            request.setAttribute("categories",categories);


            String name = "";
            for(int x = 0; x < users.size(); x++){
                    String check = users.get(x).getEmail();
                    if(check.equals(email)){
                        name = users.get(x).getFirstName();
                    }
                }    
            session.setAttribute("firstname",name);

            
            String tempFirstName = "";
            String tempLastName = "";
            String tempPassword = "";
            String tempEmail = "";
            String tempCat = "";
            String action = request.getParameter("action");
            if (action != null && action.equals("edit")) {
            tempEmail = request.getParameter("user").replaceAll(" ", "+");
                for(int x = 0; x < users.size(); x++){
                    String check = users.get(x).getEmail() ;
                    if(tempEmail.equals(check)){
                        tempFirstName = users.get(x).getFirstName();
                        tempLastName = users.get(x).getLastName();
                        tempPassword = users.get(x).getPassword();   
                    }
                }    
            }
            else if(action != null && action.equals("delete")){
                tempEmail = request.getParameter("user").replaceAll(" ", "+");
                Integer tempId;
                User owner = userService.get(tempEmail);
                List<Item> deleteList = itemService.getByOwner(owner);
                for ( int x = 0; x < deleteList.size(); x++) {
                    tempId = deleteList.get(x).getItemId();
                    itemService.delete(tempId);
                }
                String checkEmail = (String) session.getAttribute("email");
                if (tempEmail.equals(checkEmail)) {
                    userService.delete(tempEmail);
                    response.sendRedirect("login");
                } else {
                    userService.delete(tempEmail);
                    response.sendRedirect("admin");
                }
                
            }
            else if(action != null && action.equals("editCat")){
                String tempCatId = request.getParameter("cat");
                for(int x = 0; x < categories.size(); x++){
                    String check = String.valueOf(categories.get(x).getCategoryId()) ;
                    if(tempCatId.equals(check)){
                        tempCat = categories.get(x).getCategoryName();
                        session.setAttribute("currentCat", tempCatId);
//                        response.sendRedirect("admin#nav-cat");
                    }
                }    

            }
            request.setAttribute("editCat", tempCat);
            request.setAttribute("editEmail", tempEmail);
            request.setAttribute("editFirst", tempFirstName);
            request.setAttribute("editLast", tempLastName);
            request.setAttribute("editPassword", tempPassword);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        
        
        
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
        ItemService itemService = new ItemService();
        RoleService roleService = new RoleService();
        CategoryService categoryService = new CategoryService();
        List<Category> categories = null;
        List<Item> items = null;
        List<Role> roles = null;
        try {
            roles = roleService.getAll();
            categories = categoryService.getAll();
            items = itemService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        String action = request.getParameter("action");
        
        switch (action) {
            case "add":
                {
                    String email = request.getParameter("addEmail");
                    String firstName = request.getParameter("addFirst");
                    String lastName = request.getParameter("addLast");
                    String password = request.getParameter("addPassword");  
                    String roleName = request.getParameter("addRole");
                    Role role = null;
                    
                    for(int x = 0; x < roles.size(); x++){
                    if(roles.get(x).getRoleName().equals(roleName)){
                        role = roles.get(x);
                        }
                    }
                    try {
                        userService.insert(email, true, firstName, lastName, password, role);
                    }catch (Exception ex) {
                        Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            case "update":
                {
                String email = request.getParameter("editEmail");
                String firstName = request.getParameter("editFirst");
                String activeString = request.getParameter("editActive");
                boolean active;
                String lastName = request.getParameter("editLast");
                String password = request.getParameter("editPassword");
                String roleName = request.getParameter("editRole");
                
                Role role = null;
                active = activeString.equals("Yes");

                  for(int x = 0; x < roles.size(); x++)
                {
                    if(roles.get(x).getRoleName().equals(roleName))
                    {
                        role = roles.get(x);
                    }
                }
                  

                try {
                    userService.update(email, active, firstName, lastName, password, role);
                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "addCat":
            {
                Integer idCat = 1; //id automatically update to check for duplicates
                String addCat = request.getParameter("addCategory");
            try {
                categoryService.insert(idCat, addCat);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            }
            case "updateCat":
            {
                HttpSession session = request.getSession();
                Integer currentCat = Integer.parseInt((String)session.getAttribute("currentCat")) ;
                String editCat = request.getParameter("editCategory");
            try {
                categoryService.update(currentCat, editCat);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            }
            default:
                break;
        }
        response.sendRedirect("admin");
    }
}
