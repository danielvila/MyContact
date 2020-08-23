package com.dalda.mycontact;

import java.io.Serializable;

public class Contacto implements Serializable {
    private String nombre = "";
    private String nacimiento = "";
    private String telefono = "";
    private String email = "";
    private String descripcion = "";
    private int anio = 0;
    private int mes = 0;
    private int dia = 0;

    public Contacto(String nombre, String nacimiento, String telefono, String email, String descripcion, int anio, int mes, int dia) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
