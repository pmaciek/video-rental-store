package com.mpecherz.videorentalstore.video;

import com.mpecherz.videorentalstore.model.Film;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

@AllArgsConstructor
class InMemoryVideoRepository implements VideoRepository {

    private final Map<String, Film> filmsRepository;

    @Override
    public Collection<Film> getAll() {
        return filmsRepository.values();
    }

    @Override
    public Film get(String id) {
        return filmsRepository.get(id);
    }
}
