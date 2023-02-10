<%@ page import="java.sql.Connection" %>
<%@ page import="db.DBConnect" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2/4/2023
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Login Page</title>
    <script src="https://code.jquery.com/jquery-3.6.3.slim.min.js" integrity="sha256-ZwqZIVdD3iXNyGHbSYdsmWP//UBokj2FHAxKuSBKDSo=" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<%--navbar--%>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: chocolate">
    <a class="navbar-brand" href="index.jsp">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon">banalest</span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="login.jsp">Login<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="register.jsp">Register</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<%--end of navbar--%>
<%--<%--%>
<%--    Connection conn = DBConnect.getConnection();--%>
<%--    PrintWriter pw = response.getWriter();--%>
<%--    pw.println(conn);--%>
<%--%>--%>
<%--enter user plus mail--%>
<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-header text-center">
                    <i class="fa fa-user-circle-o fa-2x"></i>
                    <h5>Login Page</h5>
                </div>
                <div class="card-body">
                    <%--                enter user plus mail from bootstrap--%>
                    <form action="loginurl" method="post">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email Address</label>
                            <input type="email" class="form-control" name="mail" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Enter Password</label>
                            <input type="password" name="pass" class="form-control" id="exampleInputPassword1" required>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="from_checkbox" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block badge-pill">Submit</button>
                    </form>
                    <%--                end of enter mail and password from bootstrap--%>
                </div>
            </div>
        </div>
    </div>
</div>
<%--end of enter user plus mail--%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript">
    let status = document.getElementById("status").value;
    if(status==="register_success"){
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Your registration was a success!',
            showConfirmButton: false,
            timer: 1500
        })
    }else if(status==="empty_username"){
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'The email field is empty!',
            showConfirmButton: false,
            timer: 1500
        })
    }else if(status==="empty_password"){
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'The password field is empty!',
            showConfirmButton: false,
            timer: 1500
        })
    }else if(status==="user_unavailable"){
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Seems like you are not registered on our servers!',
                footer: '<a href="register.jsp">press here to go to the registration page</a>'
            })
    }else if(status==="passwords_not_match"){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'The password you have entered does not match the one in our servers',
            footer: '<a href="forgot_password.jsp">press here to reset password</a>'
        })
    }else if(status==="logged_out"){
        Swal.fire({
            icon: 'success',
            title: 'Exit Successful',
            text: 'You have logged out successful, please visit us again',
            footer: '<a href="forgot_password.jsp">press here to reset password</a>'
        })
    }
    status = "null";
</script>

</body>
</html>
