<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVntory - Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="p-3 mb-2 bg-info text-white">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-9">
                        <h1>HOME nVentory Item Management System</h1>
                    </div>
                    <div class="col-3">
                        <div class="row">
                            <div class="col-6">
                                <h2 class="text-right">Admin: ${firstname}</h2>
                            </div>
                            <div class="col">
                                <a class="btn btn-warning" href="login" role="button">Log Out</a>
                            </div>
                        </div>                        
                    </div>
                </div>  
            </div>
        </div>
        <div class="container">
            <nav class="nav nav-tabs flex-column">
                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                        <button class="nav-link active" id="nav-accounts-tab" data-bs-toggle="tab" data-bs-target="#nav-accounts" type="button" role="tab" aria-controls="nav-accounts"><a class="text-info">Accounts</a></button>
                        <button class="nav-link" id="nav-cat-tab" data-bs-toggle="tab" data-bs-target="#nav-cat" type="button" role="tab" aria-controls="nav-cat"><a class="text-info">Categories</a></button>
                </div>
                
            </nav>   
        </div>
        <div class="container">
           <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-accounts" role="tabpanel" aria-labelledby="nav-accounts-tab">
            
            <div class="container">
                <div class="row">
                    <div class="col-8">
                   
                        <div class="add">
                            <form action="admin" method="POST">
                                <br>
                            <h1>Add an account</h1>
                            <input type="hidden" name="action" value="add">
                            <table>
                                <tr>
                                    <td><label for="addItemName">Email: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="addEmail" required="true" ></div></td>
                                </tr>
                                <tr>
                                    <td><label for="addFirst">First Name: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="addFirst" required="true" ></div></td>
                                </tr>
                                <tr>
                                    <td><label for="addLast">Last Name: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="addLast" required="true" ></div></td>
                                </tr>
                                <tr>
                                    <td><label for="addPassword">Password: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="password" name="addPassword" required="true" ></div></td>
                                </tr>
                                <tr><td><label for="addRole">Role:</label></td>
                                    <td>
                                        <div class="form-group">
                                            <select class="form-control" id="FormControlSelect" name="addRole" required="true">
                                                <option>Select Role</option>
                                                <c:forEach var="role" items="${roles}">
                                                <option>${role.roleName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <input class="btn btn-info" type="submit" value="Add Account">
                            </form>
                        </div>
                        </div>
                    <div class="col-4">
                        <div class="edit">
                        <form action="admin" method="POST">
                            <br>
                        <h1>Edit an account</h1> 
                       <input type="hidden" name="action" value="update">
                        <table>
                                <tr>
                                    <td><label for="editEmail">Email: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="editEmail" value="<%= request.getAttribute("editEmail")%>" required="true" ></div></td>
                                </tr>
                                <tr><td><label for="editActive">Active?</label></td>
                                    <td>
                                        <div class="form-group">
                                            <select class="form-control" id="FormControlSelect" name="editActive" required="true">
                                                <option></option>
                                                <option>Yes</option>
                                                <option>No</option>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="editFirst">First Name: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="editFirst" value="<%= request.getAttribute("editFirst")%>" required="true" ></div></td>
                                </tr>
                                <tr>
                                    <td><label for="editLast">Last Name: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="editLast" value="<%= request.getAttribute("editLast")%>" required="true" ></div></td>
                                </tr>
                                <tr>
                                    <td><label for="editPassword">Password: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="password" name="editPassword" value="<%= request.getAttribute("editPassword")%>" required="true" ></div></td>
                                </tr>
                                <tr><td><label for="editRole">Role:</label></td>
                                    <td>
                                        <div class="form-group">
                                            <select class="form-control" id="FormControlSelect" name="editRole" required="true">
                                                <option>Select Role</option>
                                                <c:forEach var="role" items="${roles}">
                                                <option>${role.roleName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <input class="btn btn-info" type="submit" value="    Confirm    ">
                        </form>
                        </div>
                            </div>
                </div>
            </div> 
                
                
            <div class="container">
               <div class="row">
                    <div class="col">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Email</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Password</th>
                                    <th>Role</th>
                                    <th>Active</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.email}</td>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.password}</td>
                                        <td>${user.role.roleName}</td>
                                        <td>${user.active ? "Yes" : "No"}</td>
                                        <td>
                                            <a class="btn btn-info" role="button" href="admin?action=edit&user=${user.email}">Edit
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                            </svg>
                                            </a>
                                            <a class="btn btn-warning" role="button" href="admin?action=delete&user=${user.email}">Delete
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                            </svg>
                                            </a>                                          
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> 
            </div>
            

            </div>
               
            <div class="tab-pane fade" id="nav-cat" role="tabpanel" aria-labelledby="nav-cat-tab">
                <div class="container">
                <div class="row">
                    <div class="col-4">
                   
                        <div class="add">
                            <form action="admin" method="POST">
                                <br>
                            <h1>Add a Category</h1>
                            <input type="hidden" name="action" value="addCat">
                            <table>
                                <tr>
                                    <td><label for="addCategory">Name: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="addCategory" required="true" ></div></td>
                                </tr>
                            </table>
                            <br>
                            <input class="btn btn-info" type="submit" value="Add">
                            </form>
                        </div>
                        
                        <div class="edit">
                        <form action="admin" method="POST">
                            <br>
                        <h1>Edit a Category</h1> 
                       <input type="hidden" name="action" value="updateCat">
                        <table>
                                <tr>
                                    <td><label for="editCategory">Name: </label></td>
                                    <td><div class="form-group"><input class="form-control" type="text" name="editCategory" value="<%= request.getAttribute("editCat")%>" required="true" ></div></td>
                                </tr>

                            </table>
                            <br>
                            <input class="btn btn-info" type="submit" value="Confirm">
                        </form>
                        </div>
                        
                        
                        
                        </div>
                    
                                
                                
                                
                       <div class="col-8">
               <div class="row">
                    <div class="col">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Category Name</th>
                                    <th>Edit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${categories}" var="category">
                                    <tr>
                                        <td>${category.categoryId}</td>
                                        <td>${category.categoryName}</td>
                                        <td>
                                           <a class="btn btn-info" role="button" href="admin?action=editCat&cat=${category.categoryId}">Edit
                                           <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                           </svg>
                                           </a>                                      
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> 
            </div>              
            </div>
                                
           </div>
            </div> 
                
                
            
                                   
    </body>
</html>
