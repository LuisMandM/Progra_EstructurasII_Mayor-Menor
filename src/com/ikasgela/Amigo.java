package com.ikasgela;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Amigo implements Comparable {
    private String nombre;
    private LocalDate fecha_Nacimiento;


    //Constructors
    public Amigo(String nombre, LocalDate fecha_Nacimiento) {
        this.nombre = nombre;
        this.fecha_Nacimiento = fecha_Nacimiento;
    }

    public Amigo() {
    }

    //Getters and Setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_Nacimiento() {
        return fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(LocalDate fecha_Nacimiento) {
        this.fecha_Nacimiento = fecha_Nacimiento;
    }


    //Utilities
    public int Edad() {
        return (int) ChronoUnit.YEARS.between(this.fecha_Nacimiento, LocalDate.now());
        //(int) ChronoUnit.DAYS.between(this.fecha_Nacimiento, LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amigo amigo = (Amigo) o;
        return Objects.equals(nombre.toLowerCase(), amigo.nombre.toLowerCase()) && Objects.equals(fecha_Nacimiento, amigo.fecha_Nacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fecha_Nacimiento);
    }

    @Override
    public int compareTo(Object o) {
        Amigo amigo_Compare = (Amigo) o;
        if (this.Edad() > amigo_Compare.Edad()) return 1;
        else if (this.Edad() < amigo_Compare.Edad()) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%-25s | %-5s Años", nombre, Edad());
    }
}
