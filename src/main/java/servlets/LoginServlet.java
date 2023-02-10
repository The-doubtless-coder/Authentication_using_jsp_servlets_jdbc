package servlets;

import dao.SessionDAO;
import dao.UserDAO;
import db.DBConnect;
import entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginServlet", value = "/loginurl")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
//        SessionDAO sessionnDAO = new SessionDAO(DBConnect.getConnection());
        RequestDispatcher rd = null;
        boolean isAvailable = false;
        boolean isSimillar = false;
        HttpSession session =null;
        User user = null;
        String uuid = " ";
        String username = request.getParameter("mail").trim();
        String password = request.getParameter("pass").trim();

//        validations
        if(username.length()<1){
            rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("status", "empty_username");
        }
        if(password.length()<1){
            rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("status", "empty_password");
        }
        if(rd!=null) {
            rd.forward(request, response);
        }
        isAvailable = UserDAO.findUser(username);
        if(!isAvailable){
            rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("status", "user_unavailable");
            rd.forward(request, response);
        }
        isSimillar = UserDAO.comparePasswords(username, password);
        if(!isSimillar){
            rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("status", "passwords_not_match");
        }else {
            uuid = UUID.randomUUID().toString();
            session = request.getSession();
            session.setAttribute("session_id", uuid);
            SessionDAO.check_whether_session_exists(username);
            SessionDAO.save_user_session(username, uuid);
            user = UserDAO.returnUser(username);
            session.setAttribute("user_obj", user);
            rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("status", "successful_login");
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
    }
}
