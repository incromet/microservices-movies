/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.movies.services;

/**
 *
 * @author 2104784
 */
public interface MicroserviceMoviesServices {
    /**
     * Obtiene los datos de una pelicula.
     * @param city la pelicula a consultar
     * @return String que representa el JSON de los datos del clima.
     * @throws MicroserviceMoviesException 
     */
    String getMovie(String movie, String year) throws MicroserviceMoviesException;
}
