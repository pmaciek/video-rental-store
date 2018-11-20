package com.mpecherz.videorentalstore.model;

import com.mpecherz.videorentalstore.model.Film;

import java.util.List;
import java.util.Optional;


public interface VideoService {

    List<Film> getAll();

    Optional<Film> get(String id);
}
