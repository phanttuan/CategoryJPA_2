package controller;

import entity.Video;
import service.IVideoService;
import service.impl.VideoService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VideoController extends HttpServlet {
    @Inject
    private IVideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        switch (action) {
            case "add":
                req.getRequestDispatcher("/views/admin/video/add.jsp").forward(req, resp);
                break;
            case "edit":
                Long id = Long.valueOf(req.getParameter("id"));
                Video video = videoService.findById(id);
                req.setAttribute("video", video);
                req.getRequestDispatcher("/views/admin/video/edit.jsp").forward(req, resp);
                break;
            case "search":
                String keyword = req.getParameter("keyword");
                List<Video> searchResults = videoService.searchByTitle(keyword);
                req.setAttribute("videos", searchResults);
                req.getRequestDispatcher("/views/admin/video/list.jsp").forward(req, resp);
                break;
            default:
                List<Video> videos = videoService.findAll();
                req.setAttribute("videos", videos);
                req.getRequestDispatcher("/views/admin/video/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "add";
        switch (action) {
            case "add":
                Video video = new Video();
                video.setTitle(req.getParameter("title"));
                video.setDescription(req.getParameter("description"));
                video.setUrl(req.getParameter("url"));
                video.setCreatedDate(new java.util.Date());
                videoService.insert(video);
                resp.sendRedirect(req.getContextPath() + "/admin/video");
                break;
            case "edit":
                Long id = Long.valueOf(req.getParameter("id"));
                Video editVideo = videoService.findById(id);
                editVideo.setTitle(req.getParameter("title"));
                editVideo.setDescription(req.getParameter("description"));
                editVideo.setUrl(req.getParameter("url"));
                videoService.update(editVideo);
                resp.sendRedirect(req.getContextPath() + "/admin/video");
                break;
            case "delete":
                Long deleteId = Long.valueOf(req.getParameter("id"));
                videoService.delete(deleteId);
                resp.sendRedirect(req.getContextPath() + "/admin/video");
                break;
        }
    }
}