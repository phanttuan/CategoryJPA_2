package service;

import entity.Video;
import java.util.List;

public interface IVideoService {
    void insert(Video video);
    void update(Video video);
    void delete(Long id);
    Video findById(Long id);
    List<Video> findAll();
    List<Video> searchByTitle(String keyword);
}
