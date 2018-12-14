/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.movies.persistence.impl;

import edu.eci.arsw.movies.model.HttpConnection;
import edu.eci.arsw.movies.persistence.MicroserviceMoviesPersistence;
import edu.eci.arsw.movies.services.MicroserviceMoviesException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2104784
 */
@Service
public class InMemoryPersistence implements MicroserviceMoviesPersistence {
    
    @Autowired
    HttpConnection externalAPI;
    
    private Map<String, String> cache;
    
    public InMemoryPersistence() {
        cache = new ConcurrentHashMap<>();
    }
    
    @Override
    public String getMovie(String movie, String year) throws MicroserviceMoviesException {
        if(cache.containsKey(movie)){
            return cache.get(movie);
        }else{
            String mov = externalAPI.getMovie(movie, year);
            cache.put(movie, mov);
            return mov;
        }
    }

}
