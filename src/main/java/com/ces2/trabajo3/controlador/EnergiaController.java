package com.ces2.trabajo3.controlador;

import com.ces2.trabajo3.modelo.BancoDeEnergia;
import com.ces2.trabajo3.modelo.Energia;
import com.ces2.trabajo3.repositories.EnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ces2.trabajo3.modelo.EnumEnergia;

import java.util.function.Function;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;
import java.util.ListIterator;

@Controller
public class EnergiaController {

    @Autowired
    private EnergiaRepository energiaRepository;

    private List<String> resultado = new ArrayList<>();
    // Funcion que recibe un objeto Energia y retorna la suma de todos
    // los datos de su Stack conteo.
    private final Function<Energia, Integer> sumaConteo = energia -> energia.getConteo().stream()
            .mapToInt(Integer::intValue)
            .sum();

    // Punto 1: crear los 5 objetos Energia, el Stack con ellos,
    // y el BancoDeEnergia con su Deque lleno.
    @GetMapping("punto1")
    public String punto1(Model model) {

        resultado.clear();

        Stack<Energia> stackEnergias = energiaRepository.crearStackEnergias();
        BancoDeEnergia banco = energiaRepository.crearBanco(stackEnergias);

        resultado.add("Se creo un Stack con " + stackEnergias.size() + " objetos Energia");
        resultado.add("Se creo el objeto: " + banco.toString());
        resultado.add("Objetos en el Deque del banco: " + banco.getEnergias().size());

        model.addAttribute("resultado", resultado);

        return "resultado";
    }
    // Punto 2: ListIterator sobre el Stack de los 5 objetos e
    // Iterator sobre el Deque del banco.
    @GetMapping("punto2")
    public String punto2(Model model) {

        resultado.clear();

        Stack<Energia> stackEnergias = energiaRepository.crearStackEnergias();
        BancoDeEnergia banco = energiaRepository.crearBanco(stackEnergias);

        // ListIterator con el Stack (Stack es una List, tiene listIterator())
        resultado.add("ListIterator sobre el Stack de Energia:");

        ListIterator<Energia> listIterator = stackEnergias.listIterator();

        while (listIterator.hasNext()) {
            Energia energia = listIterator.next();
            resultado.add("Nombre: " + energia.getNombre()
                    + " | Capacidad MW: " + energia.getCapacidadMW()
                    + " | Emisiones CO2 por kWh: " + energia.getEmisionesCO2PorKWh()
                    + " | Tipo de energia: " + energia.getTipoEnergia()
                    + " | Categoria: " + energia.getCategoria()
                    + " | Conteo: " + energia.getConteo());
        }

        // Iterator con el Deque del banco (usando el metodo iterator())
        resultado.add("Iterator sobre el Deque del banco " + banco.getNombre() + ":");

        Iterator<Energia> iterator = banco.getEnergias().iterator();

        while (iterator.hasNext()) {
            Energia energia = iterator.next();
            resultado.add("Nombre: " + energia.getNombre()
                    + " | Capacidad MW: " + energia.getCapacidadMW()
                    + " | Emisiones CO2 por kWh: " + energia.getEmisionesCO2PorKWh()
                    + " | Tipo de energia: " + energia.getTipoEnergia()
                    + " | Categoria: " + energia.getCategoria()
                    + " | Conteo: " + energia.getConteo());
        }

        model.addAttribute("resultado", resultado);

        return "resultado";
    }

    // Punto 3: programacion funcional sobre el Deque del banco para
    // imprimir los datos de los objetos de un enum especifico (SOLAR).
    @GetMapping("punto3")
    public String punto3(Model model) {

        resultado.clear();

        Stack<Energia> stackEnergias = energiaRepository.crearStackEnergias();
        BancoDeEnergia banco = energiaRepository.crearBanco(stackEnergias);

        // Conversion de Stack a Deque, como indica el enunciado
        Deque<Energia> dequeConvertido = new ArrayDeque<>(stackEnergias);
        resultado.add("Stack convertido a Deque con " + dequeConvertido.size() + " objetos");

        // Filtrar por el enum SOLAR usando el Deque del banco
        resultado.add("Objetos del banco con tipo de energia SOLAR:");

        banco.getEnergias().stream()
                .filter(e -> e.getTipoEnergia() == EnumEnergia.SOLAR)
                .forEach(e -> resultado.add("Nombre: " + e.getNombre()
                        + " | Capacidad MW: " + e.getCapacidadMW()
                        + " | Emisiones CO2 por kWh: " + e.getEmisionesCO2PorKWh()
                        + " | Tipo de energia: " + e.getTipoEnergia()
                        + " | Categoria: " + e.getCategoria()
                        + " | Conteo: " + e.getConteo()));

        // Mismo filtro con otro enum, para comprobar que el filtro funciona
        long cantidadCarbon = banco.getEnergias().stream()
                .filter(e -> e.getTipoEnergia() == EnumEnergia.CARBON)
                .count();

        resultado.add("Objetos del banco con tipo de energia CARBON: " + cantidadCarbon);

        model.addAttribute("resultado", resultado);

        return "resultado";
    }

    // Punto 4: llamar a la funcion sumaConteo con apply() para cada objeto.
    @GetMapping("punto4")
    public String punto4(Model model) {

        resultado.clear();

        Stack<Energia> stackEnergias = energiaRepository.crearStackEnergias();

        resultado.add("Suma de los datos del Stack conteo de cada objeto, con Function.apply():");

        for (Energia energia : stackEnergias) {
            Integer total = sumaConteo.apply(energia);
            resultado.add(energia.getNombre()
                    + " | conteo: " + energia.getConteo()
                    + " | suma total: " + total);
        }

        model.addAttribute("resultado", resultado);

        return "resultado";
    }
    // Punto 5: un objeto Energia ya creado llama a verTotales y le
    // pasa la funcion sumaConteo del Punto 4.
    @GetMapping("punto5")
    public String punto5(Model model) {

        resultado.clear();

        Stack<Energia> stackEnergias = energiaRepository.crearStackEnergias();

        // Tercer objeto del Stack (Parque Solar Costa)
        Energia energia = stackEnergias.get(2);

        resultado.add("Objeto que llama a verTotales: " + energia.getNombre());
        resultado.add("Conteo de ese objeto: " + energia.getConteo());
        resultado.add(energia.verTotales(sumaConteo));

        model.addAttribute("resultado", resultado);

        return "resultado";
    }
}