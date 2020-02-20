package org.cinemaFX.control;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

public class Film implements Serializable {

    @XmlElement(name="INTERPRETS")
    String interprets;

    @XmlElement(name="IDFILM")
    int idFilm;

    @XmlElement(name="TITOL")
    String titol;

    @XmlElement(name="ANY")
    int any;

    @XmlElement(name="DIRECCIO")
    String direccio;

    @XmlElement(name="SINOPSI")
    String sinopsi;

    @XmlElement(name = "CARTELL")
    private String image;

    @XmlElement(name="IDIOMA_x0020_ORIGINAL")
    String idioma;

    String buscar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(idioma, film.idioma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idioma);
    }

    public Film() {
        super();
    }

    @Override
    public String toString() {
        return  "titol: " + titol + "\n" +
                "Any: " + any + "\n" +
                "Direccio: " + direccio + "\n";
    }

    public String getImage() {
        return image;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public String getTitol() {
        return titol;
    }

    public int getAny() {
        return any;
    }

    public String getDireccio() {
        return direccio;
    }

    public String getSinopsi() {
        return sinopsi;
    }

    public String getInterprets() { return interprets; }

    public String getIdioma() {
        return idioma;
    }
}

