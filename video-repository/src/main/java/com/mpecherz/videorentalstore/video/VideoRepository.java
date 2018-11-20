package com.mpecherz.videorentalstore.video;


import com.mpecherz.videorentalstore.model.Film;

import java.util.Collection;

interface VideoRepository {
    Collection<Film> getAll();

    Film get(String id);
}
