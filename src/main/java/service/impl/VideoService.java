package service.impl;

import dao.IVideoDAO;
import dao.impl.VideoDAO;
import entity.Video;
import service.IVideoService;

import java.util.List;

public class VideoService implements IVideoService {
    private IVideoDAO videoDAO;

    public VideoService() {
        this.videoDAO = new VideoDAO();
    }

    @Override
    public void insert(Video video) {
        videoDAO.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDAO.update(video);
    }

    @Override
    public void delete(Long id) {
        videoDAO.delete(id);
    }

    @Override
    public Video findById(Long id) {
        return videoDAO.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    @Override
    public List<Video> searchByTitle(String keyword) {
        return videoDAO.searchByTitle(keyword);
    }
}