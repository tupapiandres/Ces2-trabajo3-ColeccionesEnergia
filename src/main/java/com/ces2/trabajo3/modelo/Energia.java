package com.ces2.trabajo3.modelo;

import java.util.Stack;
import java.util.function.Function;

public class Energia {

    private String nombre;
    private double capacidadMW;
    private double emisionesCO2PorKWh;
    private EnumEnergia tipoEnergia;
    private Categoria categoria;

    // Produccion diaria de esta unidad, en MWh: cada entero es un dia.
    private Stack<Integer> conteo = new Stack<>();

    // Constructor vacio
    public Energia() {
    }

    // Constructor con los atributos y los dos enum
    public Energia(String nombre, double capacidadMW, double emisionesCO2PorKWh,
                   EnumEnergia tipoEnergia, Categoria categoria) {
        this.nombre = nombre;
        this.capacidadMW = capacidadMW;
        this.emisionesCO2PorKWh = emisionesCO2PorKWh;
        this.tipoEnergia = tipoEnergia;
        this.categoria = categoria;
    }

    // Metodo para agregar datos al Stack conteo
    public void agregarConteo(Integer dato) {
        conteo.push(dato);
    }

    // ----- set -----
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidadMW(double capacidadMW) {
        this.capacidadMW = capacidadMW;
    }

    public void setEmisionesCO2PorKWh(double emisionesCO2PorKWh) {
        this.emisionesCO2PorKWh = emisionesCO2PorKWh;
    }

    public void setTipoEnergia(EnumEnergia tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setConteo(Stack<Integer> conteo) {
        this.conteo = conteo;
    }

    // ----- get -----
    public String getNombre() {
        return nombre;
    }

    public double getCapacidadMW() {
        return capacidadMW;
    }

    public double getEmisionesCO2PorKWh() {
        return emisionesCO2PorKWh;
    }

    public EnumEnergia getTipoEnergia() {
        return tipoEnergia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Stack<Integer> getConteo() {
        return conteo;
    }

    // Recibe la funcion del Punto 4 y la usa para calcular el total
    // de datos enteros del objeto que llama al metodo.
    public String verTotales(Function<Energia, Integer> funcion) {
        return "Total de datos enteros de " + nombre + ": " + funcion.apply(this);
    }

    @Override
    public String toString() {
        return "Energia{nombre=" + nombre
                + ", capacidadMW=" + capacidadMW
                + ", emisionesCO2PorKWh=" + emisionesCO2PorKWh
                + ", tipoEnergia=" + tipoEnergia
                + ", categoria=" + categoria
                + ", conteo=" + conteo + "}";
    }
}