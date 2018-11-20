package com.mpecherz.videorentalstore.video;

import com.mpecherz.videorentalstore.model.VideoService;
import com.mpecherz.videorentalstore.model.Film;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Override
    public List<Film> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(videoRepository.getAll()));
    }

    @Override
    public Optional<Film> get(String id) {
        return Optional.ofNullable(videoRepository.get(id));
    }
}
