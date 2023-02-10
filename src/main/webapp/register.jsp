<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2/8/2023
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="css/style.css">
  <title>My Registration Page</title>
  <script src="https://code.jquery.com/jquery-3.6.3.slim.min.js" integrity="sha256-ZwqZIVdD3iXNyGHbSYdsmWP//UBokj2FHAxKuSBKDSo=" crossorigin="anonymous"></script>
  <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body style="background-color: aquamarine">
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
<%--the nav beginning--%>
<header>
  <nav class="navbar navbar-expand-lg navbar-dark" id="nav" style="background-color: chocolate">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="login.jsp">Login</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Dropdown
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="#">Action1</a></li>
              <li><a class="dropdown-item" href="#">Action2</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item" href="#">Action3</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
          </li>
        </ul>
<%--        <%--%>
<%--       String outer = request.getAttribute("status").toString();--%>
<%--          PrintWriter pw = response.getWriter();--%>
<%--          pw.println(outer);--%>
<%--        %>--%>
        <form class="d-flex">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
      </div>
    </div>
  </nav>
</header>
<%--the nav ending peopel--%>
<section class="vh-100" style="background-color: #eee;">
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px;">
          <div class="card-body p-md-5">
            <div class="row justify-content-center">
              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                <form class="mx-1 mx-md-4" action="registrationUrl" method="POST">
<%--                  alert using bootstrap--%>
                  <%
                    String msg = (String)session.getAttribute("reg_mgs");
                    if(msg!=null){
                  %>
                  <div class="alert alert-primary" role="alert">
                    <%=msg %> <a href="login.jsp" class="alert-link">a link to the login page</a>. Give it a click if you like.
                  </div>
                  <%}
                  %>
<%--  end of alert using bootstrap--%>
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="f_name" name="f_name" class="form-control" required autofocus/>
                      <label class="form-label" for="f_name">First Name</label>
                    </div>
                  </div>
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="l_name" name="l_name" class="form-control" required/>
                      <label class="form-label" for="l_name">Last Name</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="email" id="y_email" class="form-control" name="mail" required/>
                      <label class="form-label" for="y_email" >Your Email</label>
                    </div>
                  </div>
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="password" id="y_pass" class="form-control" name="pass" required />
                      <label class="form-label" for="y_pass">Password</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="password" id="rp_pass" class="form-control" name="rp_pass" required />
                      <label class="form-label" for="rp_pass">Repeat your password</label>
                    </div>
                  </div>

                  <div class="form-check d-flex justify-content-center mb-5">
                    <input class="form-check-input me-2" type="checkbox" value="" id="check_box" name="from_checkbox" />
                    <label class="form-check-label" for="check_box">
                      I agree all statements in <a href="#!">Terms of service</a>
                    </label>
                  </div>

                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                    <button type="submit" class="btn btn-primary btn-lg">Register</button>
                  </div>

                </form>

              </div>
              <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                     class="img-fluid" alt="Sample image">

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<%--<script src="https://common.olemiss.edu/_js/sweet-alert/sweet-alert.min.js"></script>--%>
<%--<link rel="stylesheet" type="text/css" href="https://common.olemiss.edu/_js/sweet-alert/sweet-alert.css">--%>
<script type="text/javascript">
  const status = document.getElementById("status").value;
  if(status==="empty_first_name"){
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Something went wrong!',
              footer: '<a href="">You did not input Your first Name Correctly</a>'
            })
}else if(status==="empty_last_name"){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!',
      footer: '<a href="">You did not input Your Last Name Correctly</a>'
    })
  }else if(status==="null_email"){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!',
      footer: '<a href="">You did not input Your Email Correctly</a>'
    })
  }else if(status==="not_genuine_email"){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!',
      footer: '<a href="">You did not Input a Genuine Email</a>'
    })
  }else if(status==="empty_pass"){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!',
      footer: '<a href="">Youve entered nothing in the passwords field</a>'
    })
  }else if(status==="passwords_no_match"){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!',
      footer: '<a href="">Your passwords do not match</a>'
    })
  }else if(status==="register_failed"){
            Swal.fire({
              position: 'top-end',
              icon: 'error',
              title: 'Registration was unsuccessful',
              showConfirmButton: false,
              timer: 1500
            })
  }else if(status==="result_set_is_empty"){
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: 'Youve used a duplicate email ID',
      showConfirmButton: false,
      timer: 1500
    })
  }else if(status==="empty_checkbox"){
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: 'You did not agree to the terms',
      showConfirmButton: true
    })
  }
</script>
</body>
</html>
