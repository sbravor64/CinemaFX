package org.cinemaFX.control;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ConexionXML {
    List<Film> films;
    List<Cinema> cinemas;
    List<Cicle> cicles;
    List<Sesion> sesions;

    public void connectedFilms() throws JAXBException, IOException {
        URL url = new URL("http://gencat.cat/llengua/cinema/provacin.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        films = ((Films)jaxbUnmarshaller.unmarshal(is)).filmsList;
    }

    public void connectedCinema() throws JAXBException, IOException {
        URL url = new URL("http://gencat.cat/llengua/cinema/cinemes.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Cinemas.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        cinemas = ((Cinemas)jaxbUnmarshaller.unmarshal(is)).cinemaList;
    }

    public void connectedCicles() throws JAXBException, IOException {
        URL url = new URL("http://gencat.cat/llengua/cinema/cicles.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Cicles.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        cicles = ((Cicles)jaxbUnmarshaller.unmarshal(is)).ciclesList;
    }

    public void connectedSessions() throws JAXBException, IOException {
        URL url = new URL("http://www.gencat.cat/llengua/cinema/film_sessions.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Sesions.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        sesions = ((Sesions)jaxbUnmarshaller.unmarshal(is)).sesionsList;
    }

    public List<Film> getFilms() {
        return films;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public List<Cicle> getCicles() {
        return cicles;
    }

    public List<Sesion> getSesions() {
        return sesions;
    }
}
