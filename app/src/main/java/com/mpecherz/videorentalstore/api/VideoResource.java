package com.mpecherz.videorentalstore.api;

import com.mpecherz.videorentalstore.model.VideoService;
import com.mpecherz.videorentalstore.model.Film;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/films")
@Slf4j
@RequiredArgsConstructor
public class VideoResource {

    private final VideoService videoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> getAll() {
        return videoService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> get(@PathVariable String id) {
        return ResponseEntity.of(videoService.get(id));
    }
}
