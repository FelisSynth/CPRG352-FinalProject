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
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        ItemService itemService = new ItemService();
        CategoryService categoryService = new CategoryService();
        
        
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            List<User> users = userService.getAll();
            
            User owner = userService.get(email);
            request.setAttribute("accEmail", email);
            request.setAttribute("accFirst", owner.getFirstName());
            request.setAttribute("accLast", owner.getLastName());
            request.setAttribute("accPw", owner.getPassword());

            List<Item> items = itemService.getByOwner(owner);
            List<Category> categories = categoryService.getAll();
            request.setAttribute("items",items);
            request.setAttribute("categories",categories);


            String name = "";
            for(int x = 0; x < users.size(); x++){
                    String check = users.get(x).getEmail();
                    if(check.equals(email)){
                        name = users.get(x).getFirstName();
                    }
                }    
            session.setAttribute("firstname",name);

            String tempItemName = "";
            String tempPrice = "";


            String action = request.getParameter("action");
            if (action != null && action.equals("edit")) {
            String itemId = request.getParameter("item");
                for(int x = 0; x < items.size(); x++){
                    String check = String.valueOf(items.get(x).getItemId()) ;
                    if(itemId.equals(check)){
                        tempItemName = items.get(x).getItemName();
                        tempPrice = String.valueOf(items.get(x).getPrice());
                        session.setAttribute("selected", itemId);
        
                    }
                }    
            }
            else if(action != null && action.equals("delete")){
                String itemId = request.getParameter("item");
                Integer id = Integer.parseInt(itemId);
                itemService.delete(id);

                response.sendRedirect("user");
            }
            
            request.setAttribute("editItemName", tempItemName);
            request.setAttribute("editPrice", tempPrice);
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
            
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
        try {
            categories = categoryService.getAll();
            items = itemService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        String action = request.getParameter("action");
        
        switch (action) {
            case "add":
                {
                    Integer id = 1;//id automatically checks for duplicates
                    HttpSession session = request.getSession();
                    String email = (String) session.getAttribute("email");//user
                    User user = null;
                    try {
                        user = userService.get(email);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }       double price;
                    String priceString = request.getParameter("addPrice");
                    String name = request.getParameter("addItemName");//name
                    String categoryString = request.getParameter("addCategory");
                    Category category = null;
                    price = Double.parseDouble(priceString);//price
                    for(int x = 0; x < categories.size(); x++){
                        if(categories.get(x).getCategoryName().equals(categoryString)){
                            category = categories.get(x);//category
                        }
                    }   try {
                        
                        itemService.insert(id, category, name, price, user);
                    }catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    request.setAttribute("activeTab", "itemsTab");
                    break;
                }
            case "update":
                {
                    HttpSession session = request.getSession();
                    String idString = (String) session.getAttribute("selected");
                    Integer id = Integer.parseInt(idString);
                    String email = (String) session.getAttribute("email");
                    String categoryString = request.getParameter("editCategory");
                    String name = request.getParameter("editItemName");
                    String priceString = request.getParameter("editPrice");
                    Double price = Double.parseDouble(priceString);
                    User user = null;
                    Category category = null;
                    for(int x = 0; x < categories.size(); x++)
                    {
                        if(categories.get(x).getCategoryName().equals(categoryString))
                        {
                            category = categories.get(x);
                        }
                    }   try {
                        user = userService.get(email);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        itemService.update(id, category, name, price, user);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    request.setAttribute("activeTab", "accountTab");
                    break;
                }
            case "account":
                {
                    String email = request.getParameter("accEmail");
                    String firstname = request.getParameter("accFirst");
                    String lastname = request.getParameter("accLast");
                    String password = request.getParameter("accPw");
                    Integer userRole = 2;
                    Role role = null;
                    try {
                        role = roleService.get(userRole);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        userService.update(email, true, firstname, lastname, password, role);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    request.setAttribute("activeTab", "accountTab");
                    break;
                }
            case "deactivate":
                {
                    HttpSession session = request.getSession();
                    String email = (String) session.getAttribute("email");
                    User owner = null;
                    try {
                        owner = userService.get(email);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        userService.update(email, false, owner.getFirstName(), owner.getLastName(), owner.getPassword(), owner.getRole());
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    request.setAttribute("activeTab", "accountTab");
                    session.invalidate();
                    break;
                }
            default:
                break;
        }
        response.sendRedirect("user");
    }
}
