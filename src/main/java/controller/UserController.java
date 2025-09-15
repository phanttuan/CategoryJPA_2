package controller;

import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        switch (action) {
            case "add":
                req.getRequestDispatcher("/views/admin/user/add.jsp").forward(req, resp);
                break;
            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                User user = userService.findById(editId);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);
                break;
            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                userService.delete(deleteId);
                resp.sendRedirect(req.getContextPath() + "/admin/user");
                break;
            default:
                List<User> users = userService.findAll();
                req.setAttribute("users", users);
                req.getRequestDispatcher("/views/admin/user/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "add";
        switch (action) {
            case "add":
                User newUser = new User();
                newUser.setUsername(req.getParameter("username"));
                newUser.setPassword(req.getParameter("password"));
                newUser.setRole(req.getParameter("role"));
                userService.save(newUser);
                resp.sendRedirect(req.getContextPath() + "/admin/user");
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                User editUser = userService.findById(id);
                editUser.setUsername(req.getParameter("username"));
                editUser.setPassword(req.getParameter("password"));
                editUser.setRole(req.getParameter("role"));
                userService.update(editUser);
                resp.sendRedirect(req.getContextPath() + "/admin/user");
                break;
        }
    }
}