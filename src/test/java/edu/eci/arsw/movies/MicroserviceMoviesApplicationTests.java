package edu.eci.arsw.movies;

import edu.eci.arsw.movies.persistence.MicroserviceMoviesPersistence;
import edu.eci.arsw.movies.services.MicroserviceMoviesException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MicroserviceMoviesApplicationTests {

    @Autowired
    MicroserviceMoviesPersistence services;
    
    @LocalServerPort
    private int port;

    String queryMovie;

    @Before
    public void initial() throws MicroserviceMoviesException {
        queryMovie = services.getMovie("Beetlejuice", "1988");
    }

    @Test
    public void contextLoads() throws MicroserviceMoviesException {

    }

    /**
     * concurrentQueryToPersitence Pruebas concurrentes a la persistenia de la
     * aplicacion.
     *
     * @throws MicroserviceMoviesException
     */
    @Test
    public void concurrentQueryToPersitence() throws MicroserviceMoviesException {
        
        List<Thread> threads = new ArrayList<>();
        int numThreads = 10;
        for (int i = 0; i < numThreads; i++) {
            threads.add(new ThreadTest());
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(MicroserviceMoviesApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class ThreadTest extends Thread {

        @Override
        public void run() {
            String queryB;
            try {
                queryB = services.getMovie("Beetlejuice", "1988");
                Assert.assertTrue(queryMovie.equals(queryB));
            } catch (MicroserviceMoviesException ex) {
                Logger.getLogger(MicroserviceMoviesApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public class ThreadTest2 extends Thread {
        
        private final String USER_AGENT = "Mozilla/5.0";

        @Override
        public void run() {
            String queryC;
            String GET_URL = "http://localhost:" + port + "/movies/Beetlejuice/1988";
            URL obj = null;
            try {
                obj = new URL(GET_URL);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                int responseCode = con.getResponseCode();
                Assert.assertEquals(201,responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                StringBuffer response = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Assert.assertEquals("{\"movie\":Beetlejuice,\"year\":\"1988\"}",response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

