<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
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
            <div class="container-fluid">
                <div class="text-center">
                    <h1 class="text-end">Login</h1></div>
                </div>
                
            
            
            <div class="d-flex justify-content-center">
            <div class="text-left">
                    <form action="login" method="post">
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
            <input class="btn btn-info" type="submit" value="Sign in">
            <br>
            <a>Don't have an account?</a>
            <a href="create">Create an account</a>
            </form>
                </div>
                
            

            </div>
            
            
            
            
        </div>
        
        
    </body>
</html>
