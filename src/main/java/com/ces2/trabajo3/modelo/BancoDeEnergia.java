package com.ces2.trabajo3.modelo;

import java.util.ArrayDeque;
import java.util.Deque;

public class BancoDeEnergia {

    private String nombre;
    private String ubicacion;
    private EnumEnergia tipoEnergia;
    private Categoria categoria;

    // Deque donde se guardan los objetos Energia
    private Deque<Energia> energias = new ArrayDeque<>();

    // Constructor vacio
    public BancoDeEnergia() {
    }

    // Constructor con los atributos y los dos enum
    public BancoDeEnergia(String nombre, String ubicacion,
                          EnumEnergia tipoEnergia, Categoria categoria) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipoEnergia = tipoEnergia;
        this.categoria = categoria;
    }

    // Metodo para agregar objetos Energia al Deque
    public void agregarEnergia(Energia energia) {
        energias.addLast(energia);
    }

    // ----- set -----
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTipoEnergia(EnumEnergia tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setEnergias(Deque<Energia> energias) {
        this.energias = energias;
    }

    // ----- get -----
    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public EnumEnergia getTipoEnergia() {
        return tipoEnergia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Deque<Energia> getEnergias() {
        return energias;
    }

    @Override
    public String toString() {
        return "BancoDeEnergia{nombre=" + nombre
                + ", ubicacion=" + ubicacion
                + ", tipoEnergia=" + tipoEnergia
                + ", categoria=" + categoria
                + ", energias=" + energias.size() + " objetos}";
    }
}