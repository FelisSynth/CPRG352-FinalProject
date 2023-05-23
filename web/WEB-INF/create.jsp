<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create an Account</title>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        <div class="p-3 mb-2 bg-info text-white">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-9">
                        <h1>HOME nVentory Item Management System</h1>
                    </div>
                </div>  
            </div>
        </div>
        <div class="container">
            <a href="login">< Back to Log in</a>
        </div>
        <div class="d-flex justify-content-center">
            <div class="add">
                <form action="create" method="POST">
                <h1>Create new Account</h1>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Email address</label>
                    <input type="email" name="addEmail"  class="form-control" id="FormControlEmail" placeholder="name@example.com">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">First Name</label>
                    <input type="text" name="addFirst" class="form-control" id="FormControlInput1">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Last Name</label>
                    <input type="text" name="addLast" class="form-control" id="FormControlInput2">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Password</label>
                    <input type="password" name="addPassword" class="form-control" id="FormControlPassword">
                </div>
                <input class="btn btn-info" type="submit" value="Submit">
                </form>
            </div>
        </div>
           <div class="d-flex justify-content-center">
            <h5>${message}</h5>
           </div>
                
        
    </body>
</html>
