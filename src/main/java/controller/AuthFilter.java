package controller;

import java.io.IOException;

import entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/user/*", "/manager/*", "/admin/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        User user = null;
        if (session != null) {
            Object u = session.getAttribute("user");
            if (u instanceof User) user = (User) u;
        }

        String uri = req.getRequestURI();
        String context = req.getContextPath();

        if (user == null) {
            resp.sendRedirect(context + "/login");
            return;
        }

        int role = user.getRoleid();
        if (uri.startsWith(context + "/admin") && role != 3) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (uri.startsWith(context + "/manager") && role != 2) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (uri.startsWith(context + "/user") && role != 1) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
