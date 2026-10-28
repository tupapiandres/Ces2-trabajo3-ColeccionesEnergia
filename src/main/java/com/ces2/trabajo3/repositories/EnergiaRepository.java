package com.ces2.trabajo3.repositories;

import com.ces2.trabajo3.modelo.BancoDeEnergia;
import com.ces2.trabajo3.modelo.Categoria;
import com.ces2.trabajo3.modelo.Energia;
import com.ces2.trabajo3.modelo.EnumEnergia;
import org.springframework.stereotype.Repository;

import java.util.Stack;

@Repository
public class EnergiaRepository {

    // Crea los 5 objetos Energia y los guarda en un Stack
    public Stack<Energia> crearStackEnergias() {

        // Los 5 objetos tienen los mismos enum: SOLAR y RENOVABLE
        Energia e1 = new Energia("Parque Solar Norte", 120.5, 0.0,
                EnumEnergia.SOLAR, Categoria.RENOVABLE);
        // e1 sin datos en el Stack conteo (0 datos)

        Energia e2 = new Energia("Parque Solar Sur", 95.0, 0.0,
                EnumEnergia.SOLAR, Categoria.RENOVABLE);
        e2.agregarConteo(310);

        Energia e3 = new Energia("Parque Solar Costa", 150.8, 0.0,
                EnumEnergia.SOLAR, Categoria.RENOVABLE);
        e3.agregarConteo(280);
        e3.agregarConteo(295);

        Energia e4 = new Energia("Parque Solar Andino", 80.2, 0.0,
                EnumEnergia.SOLAR, Categoria.RENOVABLE);
        e4.agregarConteo(150);
        e4.agregarConteo(160);
        e4.agregarConteo(155);

        Energia e5 = new Energia("Parque Solar Desierto", 200.0, 0.0,
                EnumEnergia.SOLAR, Categoria.RENOVABLE);
        e5.agregarConteo(400);
        e5.agregarConteo(420);
        e5.agregarConteo(410);
        e5.agregarConteo(430);

        Stack<Energia> stackEnergias = new Stack<>();
        stackEnergias.push(e1);
        stackEnergias.push(e2);
        stackEnergias.push(e3);
        stackEnergias.push(e4);
        stackEnergias.push(e5);

        return stackEnergias;
    }

    // Crea el BancoDeEnergia (mismos enum) y le agrega los 5 objetos
    public BancoDeEnergia crearBanco(Stack<Energia> stackEnergias) {

        BancoDeEnergia banco = new BancoDeEnergia("Banco Solar de Antioquia", "Medellin",
                EnumEnergia.SOLAR, Categoria.RENOVABLE);

        for (Energia energia : stackEnergias) {
            banco.agregarEnergia(energia);
        }

        return banco;
    }
}