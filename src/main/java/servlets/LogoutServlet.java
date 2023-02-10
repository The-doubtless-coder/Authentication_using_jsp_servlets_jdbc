package servlets;

import dao.SessionDAO;
import entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logouturl")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        User user = null;
        String user_email;
        if(session.getAttribute("user_obj")!=null) {
            user = (User) session.getAttribute("user_obj");
            user_email = user.getEmail();
            //todo: delete session object from Session
            session.removeAttribute("session_id");
            //todo: delete session id from db
            SessionDAO.check_whether_session_exists(user_email);
            //todo: delete user obj from Session
            session.removeAttribute("user_obj");
            session.invalidate();
            rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("status", "logged_out");
            rd.forward(request, response);
        }
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
doGet(request, response);
    }
}
