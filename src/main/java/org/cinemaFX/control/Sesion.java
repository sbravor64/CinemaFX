package org.cinemaFX.control;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Sesion implements Serializable {
    @XmlElement(name="CINEID")
    int idCinema;

    @XmlElement(name="IDFILM")
    int idFilm;

    @XmlElement(name="TITOL")
    String titulo;

    @XmlElement(name="ses_id")
    int idSesion;

    @XmlElement(name="CINENOM")
    String nomCine;

    @XmlElement(name="LOCALITAT")
    String localidad;

    @XmlElement(name="CICLEID")
    String cicleID;

    public Sesion() {
        super();
    }

    @Override
    public String toString() {
        return "Sesion{" +
                "idCinema=" + idCinema +
                ", idFilm=" + idFilm +
                ", titulo='" + titulo + '\'' +
                ", idSesion=" + idSesion +
                ", nomCine='" + nomCine + '\'' +
                ", localidad='" + localidad + '\'' +
                ", cicleID='" + cicleID + '\'' +
                '}';
    }

    public int getIdCinema() {
        return idCinema;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public String getNomCine() {
        return nomCine;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getCicleID() {
        return cicleID;
    }
}
