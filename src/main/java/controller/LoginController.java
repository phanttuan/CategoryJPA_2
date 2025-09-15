package controller;

import java.io.IOException;

import dao.IUserDAO;
import dao.impl.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IUserDAO userDao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDao.findByUsernameAndPassword(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRoleid()); 
            int role = user.getRoleid();
            String context = req.getContextPath();
            switch (role) {
                case 1:
                    resp.sendRedirect(context + "/user/home");
                    break;
                case 2:
                    resp.sendRedirect(context + "/manager/home");
                    break;
                case 3:
                    resp.sendRedirect(context + "/admin/home");
                    break;
                default:
                    resp.sendRedirect(context + "/login");
            }
        } else {
            req.setAttribute("message", "Invalid username or password!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}