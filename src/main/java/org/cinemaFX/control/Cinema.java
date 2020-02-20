package org.cinemaFX.control;

import javax.xml.bind.annotation.XmlElement;

public class Cinema {
    @XmlElement(name="CINENOM")
    String nombre;

    @XmlElement(name="CINEID")
    int idCinema;

    @XmlElement(name="CINEADRECA")
    String adreça;

    @XmlElement(name="LOCALITAT")
    String localidad;

    @XmlElement(name="PROVINCIA")
    String provincia;

    String buscar;

    @Override
    public String toString() {
        return "Cinema{" +
                "nombre='" + nombre + '\'' +
                ", idCinema='" + idCinema + '\'' +
                ", adreça='" + adreça + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", buscar='" + buscar + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public String getAdreça() {
        return adreça;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getBuscar() {
        return buscar;
    }
}
