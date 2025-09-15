package controller;

import dao.IUserDAO;
import dao.impl.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet({"/user/profile", "/manager/profile", "/admin/profile"})
@MultipartConfig
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserDAO userDao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        String rolePath = getRolePath(user);
        req.getRequestDispatcher("/views/" + rolePath + "/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String images = user.getImages();
        Part filePart = req.getPart("imageFile");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            String uploadPath = util.Constant.upload; // Sử dụng đường dẫn upload cố định
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            filePart.write(uploadPath + File.separator + fileName);
            images = fileName;
        }
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setImages(images);
        userDao.update(user);
        // Reload user from DB to ensure latest info (especially images field)
        User updatedUser = userDao.findById(user.getId());
        session.setAttribute("user", updatedUser);
        req.setAttribute("user", updatedUser);
        req.setAttribute("message", "Cập nhật thành công!");
        String rolePath = getRolePath(updatedUser);
        req.getRequestDispatcher("/views/" + rolePath + "/profile.jsp").forward(req, resp);
    }

    private String getRolePath(User user) {
        if (user.getRoleid() == 1) return "user";
        if (user.getRoleid() == 2) return "manager";
        return "admin";
    }
}