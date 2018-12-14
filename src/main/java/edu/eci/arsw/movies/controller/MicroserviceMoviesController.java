/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.movies.controller;

import edu.eci.arsw.movies.services.MicroserviceMoviesException;
import edu.eci.arsw.movies.services.MicroserviceMoviesServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 2104784
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/movies")
public class MicroserviceMoviesController {

    @Autowired
    MicroserviceMoviesServices services;
    
    /**
     * Get publica datos mediante el APIrest en la direccion /movie.
     * @param city
     * @return 
     */
    @GetMapping("/{movie}/{year}")
    public ResponseEntity<?> getMoviesHandler(@PathVariable("movie") String movie, @PathVariable("year") String year) {
        try {
            return new ResponseEntity<>(services.getMovie(movie, year), HttpStatus.ACCEPTED);
        } catch (MicroserviceMoviesException ex) {
            Logger.getLogger(MicroserviceMoviesException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al consultar", HttpStatus.NOT_FOUND);
        }
    }

}

