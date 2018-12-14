/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.movies.services;

import edu.eci.arsw.movies.persistence.MicroserviceMoviesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2104784
 */
@Service
public class MicroserviceMoviesServicesStub implements MicroserviceMoviesServices{
    @Autowired
    MicroserviceMoviesPersistence persitence;

    public MicroserviceMoviesServicesStub() {
    }

    @Override
    public String getMovie(String movie, String year) throws MicroserviceMoviesException {
        return persitence.getMovie(movie, year);
    }
}
