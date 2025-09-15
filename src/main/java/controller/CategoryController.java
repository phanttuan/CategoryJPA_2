package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import entity.Category;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.ICategoryService;
import service.impl.CategoryService;
import util.Constant;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CategoryController
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,
	    maxFileSize = 1024 * 1024 * 10, 
	    maxRequestSize = 1024 * 1024 * 50 )
@WebServlet(urlPatterns = { 
	"/admin/categories", "/admin/category", "/admin/category/add", "/admin/category/edit",
	"/admin/category/delete", "/admin/home",
	"/manager/categories", "/manager/home", "/manager/category", "/manager/category/add", "/manager/category/edit", "/manager/category/delete",
	"/user/categories", "/user/home", "/user/category", "/user/category/add", "/user/category/edit", "/user/category/delete" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		ICategoryService categoryService = new CategoryService();
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		HttpSession session = request.getSession(false);
		User current = null;
		if (session != null) {
			Object o = session.getAttribute("user");
			if (o instanceof User) current = (User) o;
		}

		if (uri.endsWith("/admin/home")) {
			response.sendRedirect(context + "/admin/categories");
			return;
		}
		if (uri.endsWith("/manager/home")) {
			response.sendRedirect(context + "/manager/categories");
			return;
		}
		if (uri.endsWith("/user/home")) {
			response.sendRedirect(context + "/user/categories");
			return;
		}

		if (uri.contains("categories") || uri.equals(context + "/admin/category") || uri.equals(context + "/manager/category") || uri.equals(context + "/user/category")) {
			List<Category> categories;
			if (uri.startsWith(context + "/manager")) {
				// manager sees only their own categories
				if (current == null) {
					response.sendRedirect(context + "/login");
					return;
				}
				categories = categoryService.findByUser(current.getId());
			} else {
				// admin and user see all categories
				categories = categoryService.findAll();
			}
			request.setAttribute("categories", categories);
			// forward to role-specific list view
			if (uri.startsWith(context + "/manager")) {
				request.getRequestDispatcher("/views/manager/category/list.jsp").forward(request, response);
			} else if (uri.startsWith(context + "/user")) {
				request.getRequestDispatcher("/views/user/category/list.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
			}
		} else if (uri.contains("add")) {
			if (uri.startsWith(context + "/manager")) request.getRequestDispatcher("/views/manager/category/add.jsp").forward(request, response);
			else if (uri.startsWith(context + "/user")) request.getRequestDispatcher("/views/user/category/add.jsp").forward(request, response);
			else request.getRequestDispatcher("/views/admin/category/add.jsp").forward(request, response);
		} else if (uri.contains("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = categoryService.findById(id);
			// only owner or admin can edit
			if (current == null || (category.getUser() != null && category.getUser().getId() != current.getId() && current.getRoleid() != 3)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			request.setAttribute("category", category);
			List<Category> categories = categoryService.findAll();
			request.setAttribute("categories", categories);
			if (uri.startsWith(context + "/manager")) request.getRequestDispatcher("/views/manager/category/edit.jsp").forward(request, response);
			else if (uri.startsWith(context + "/user")) request.getRequestDispatcher("/views/user/category/edit.jsp").forward(request, response);
			else request.getRequestDispatcher("/views/admin/category/edit.jsp").forward(request, response);
		} else if (uri.contains("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = categoryService.findById(id);
			if (current == null || (category.getUser() != null && category.getUser().getId() != current.getId() && current.getRoleid() != 3)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			categoryService.delete(id);
			// redirect back to the appropriate listing
			if (uri.startsWith(context + "/manager")) response.sendRedirect(context + "/manager/categories");
			else if (uri.startsWith(context + "/user")) response.sendRedirect(context + "/user/categories");
			else response.sendRedirect(context + "/admin/categories");
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		ICategoryService categoryService = new CategoryService();
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		HttpSession session = request.getSession(false);
		User current = null;
		if (session != null) {
			Object o = session.getAttribute("user");
			if (o instanceof User) current = (User) o;
		}
		if (uri.contains("add")) {
			// Only allow add for your own role (not admin adding for others)
			if (current == null || 
				(uri.startsWith(context + "/manager") && current.getRoleid() != 2) ||
				(uri.startsWith(context + "/user") && current.getRoleid() != 1) ||
				(uri.startsWith(context + "/admin") && current.getRoleid() != 3)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
			String name = request.getParameter("categoryname");
			Category category = new Category();
			category.setCategoryname(name);
			category.setImages("1.jpg");
			// set owner to currently logged in user if available
			if (current != null) category.setUser(current);
			String fname = "";
			String uploadPath = Constant.upload;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = request.getPart("images");
				if(part != null && part.getSize() > 0) {
					fname = part.getSubmittedFileName();
					int index = fname.lastIndexOf(".");
					String ext = fname.substring(index);
					fname = System.currentTimeMillis() + ext;
					part.write(uploadPath + File.separator + fname);
					category.setImages(fname);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			categoryService.insert(category);
			if (uri.startsWith(context + "/manager")) response.sendRedirect(context + "/manager/categories");
			else if (uri.startsWith(context + "/user")) response.sendRedirect(context + "/user/categories");
			else response.sendRedirect(context + "/admin/categories");
		} else if (uri.contains("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("categoryname");
			Category category = categoryService.findById(id);
			if (category != null) {
				// only owner or admin can update
				if (current == null || (category.getUser() != null && category.getUser().getId() != current.getId() && current.getRoleid() != 3)) {
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
				category.setCategoryname(name);
				String fname = "";
				String uploadPath = Constant.upload;
				File uploadDir = new File(uploadPath);
				if(!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				try {
					Part filePart = request.getPart("images");
					if(filePart.getSize() > 0) {
						fname = filePart.getSubmittedFileName();
						int index = fname.lastIndexOf(".");
						String ext = fname.substring(index);
						fname = System.currentTimeMillis() + ext;
						filePart.write(uploadPath + File.separator + fname);
						category.setImages(fname);
					} 
				} catch (Exception e) {
					e.printStackTrace();
				}
				categoryService.update(category);
				if (uri.startsWith(context + "/manager")) response.sendRedirect(context + "/manager/categories");
				else if (uri.startsWith(context + "/user")) response.sendRedirect(context + "/user/categories");
				else response.sendRedirect(context + "/admin/categories");
			}
		} 
	}

}