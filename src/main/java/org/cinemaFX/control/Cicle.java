package org.cinemaFX.control;

import javax.xml.bind.annotation.XmlElement;

public class Cicle {
    @XmlElement(name="CICLENOM")
    String nombre;

    @XmlElement(name="CICLEID")
    int idCiclo;

    @XmlElement(name="CICLEINFO")
    String info;

    @XmlElement(name="IMGCICLE")
    String img;

    String buscar;

    @Override
    public String toString() {
        return "Cicle{" +
                "nombre='" + nombre + '\'' +
                ", idCiclo='" + idCiclo + '\'' +
                ", info='" + info + '\'' +
                ", img='" + img + '\'' +
                ", buscar='" + buscar + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public String getInfo() {
        return info;
    }

    public String getImg() {
        return img;
    }

    public String getBuscar() {
        return buscar;
    }
}
