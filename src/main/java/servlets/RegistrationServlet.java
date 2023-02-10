package servlets;

import dao.UserDAO;
import dto.UserDTO;
import entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.postgresql.util.PSQLException;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name = "RegistrationServlet", value = "/registrationUrl")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        RequestDispatcher rd = null;
        UserDTO userDTO = null;
        boolean isRegistered;

        String firstName =request.getParameter("f_name").toLowerCase();
        String lastName =request.getParameter("l_name").toLowerCase();
        String emailAddress = request.getParameter("mail").toLowerCase();
        String password = request.getParameter("pass");
        String repeatPass = request.getParameter("rp_pass");
        String from_checkbox = request.getParameter("from_checkbox");

        if(from_checkbox==null){
            request.setAttribute("status", "empty_checkbox");
            rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }

            User user = new User();
            try {
                user.setFirstName(firstName);
            }catch (Exception e){
                System.err.println(e.getClass().getName() + " " + e.getMessage());
                request.setAttribute("status", "empty_first_name");
                rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                return;
            }
            try {
                user.setLastName(lastName);
            } catch (Exception e){
                System.err.println(e.getClass().getName() + " " + e.getMessage());
                request.setAttribute("status", "empty_last_name");
                rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                return;
            }
        try{
            user.setEmail(emailAddress);}
            catch (Exception e){
                if(e instanceof EOFException){
                    System.err.println(e.getClass().getName() + " " + e.getMessage());
                    request.setAttribute("status", "null_email");
                    rd = request.getRequestDispatcher("register.jsp");
                    rd.forward(request, response);
                    return;
                }else {
                    System.err.println(e.getClass().getName() + " " + e.getMessage());
                    request.setAttribute("status", "not_genuine_mail");
                    rd = request.getRequestDispatcher("register.jsp");
                    rd.forward(request, response);
                    return;
                }
            }
            try{
            user.setPassword(password);}
            catch(Exception e){
                System.err.println(e.getClass().getName() + " " + e.getMessage());
                request.setAttribute("status", "empty_pass");
                rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                return;
            }
            if(!password.equals(repeatPass.trim())){
                rd = request.getRequestDispatcher("register.jsp");
                request.setAttribute("status","passwords_no_match");
                rd.forward(request, response);
                return;
            }
        user.setCreatedOn(LocalDateTime.now());
            userDTO = new UserDTO(user.getFirstName(),user.getLastName(), user.getEmail(), user.getPassword(), user.getCreatedOn());
        try {
            isRegistered = UserDAO.registerUser(userDTO);
        } catch (PSQLException e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("register.jsp");
            request.setAttribute("status", "result_set_is_empty");
            rd.forward(request, response);
            return;
        }
//        HttpSession session = request.getSession();
//        if(isRegistered){
//            session.setAttribute("reg_msg", "reg_success");
//        }else {
//            session.setAttribute("reg_msg", "reg_failed");
//        }
//        response.sendRedirect("register.jsp");
        if(isRegistered){
                 rd = request.getRequestDispatcher("login.jsp");
                 request.setAttribute("status", "register_success");
        }else {
                 rd = request.getRequestDispatcher("register.jsp");
                 request.setAttribute("status", "register_failed");
        }
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
doGet(request, response);
    }
}
