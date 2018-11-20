package com.mpecherz.videorentalstore.video;

import com.mpecherz.videorentalstore.model.Film;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
class VideStoreConfiguration {

    @Bean
    InMemoryVideoRepository inMemoryVideoRepository() {

        Map<String, Film> filmsRepository = new ConcurrentHashMap<String, Film>() {
            {
                createFilm("Matrix", Film.Type.NEW);
                createFilm("Matrix 2", Film.Type.NEW);
                createFilm("Spider Man", Film.Type.REGULAR);
                createFilm("Spider Man 2", Film.Type.REGULAR);
                createFilm("Africa", Film.Type.OLD);
                createFilm("Rambo 1", Film.Type.OLD);
            }

            private void createFilm(String name, Film.Type type) {
                String uuid = UUID.randomUUID().toString();
                put(uuid, new Film(uuid, name, type));
            }
        };

        return new InMemoryVideoRepository(filmsRepository);
    }

}
