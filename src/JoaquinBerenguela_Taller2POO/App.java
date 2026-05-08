//Joaquín Berenguela || 21.708.988-3




package JoaquinBerenguela_Taller2POO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class App {
    private static ArrayList<Pokemon> pokedex = new ArrayList<>();
    private static ArrayList<Gimnasio> gimnasios = new ArrayList<>();
    private static ArrayList<AltoMando> altosMandos = new ArrayList<>();
    private static ArrayList<String> habitats = new ArrayList<>();
    private static ArrayList<Pokemon> equipoJugador = new ArrayList<>();
    private static String apodoJugador;
    private static int medallasJugador;

    private static final String[] TIPOS = {"Normal", "Fuego", "Agua", "Planta", "Electrico", "Hielo", "Lucha", "Veneno", "Tierra", "Volador", "Psiquico", "Bicho", "Roca", "Fantasma", "Dragon", "Acero", "Siniestro", "Hada"};

    private static final double[][] EFECTIVIDAD = {
        // NOR  FUE  AGU  PLA  ELE  HIE  LUC  VEN  TIE  VOL  PSI  BIC  ROC  FAN  DRA  ACE  SIN  HAD
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 0.5, 1.0, 1.0 }, // NORMAL
        {  1.0, 0.5, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0 }, // FUEGO
        {  1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // AGUA
        {  1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 0.5, 1.0, 1.0 }, // PLANTA
        {  1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // ELECTRICO
        {  1.0, 0.5, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0 }, // HIELO
        {  2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5 }, // LUCHA
        {  1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.0, 1.0, 2.0 }, // VENENO
        {  1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0 }, // TIERRA
        {  1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0 }, // VOLADOR
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0 }, // PSIQUICO
        {  1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 0.5, 2.0, 0.5 }, // BICHO
        {  1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0 }, // ROCA
        {  0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0 }, // FANTASMA
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.0 }, // DRAGON
        {  1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0 }, // ACERO
        {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5 }, // SINIESTRO
        {  1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 2.0, 1.0 }  // HADA
    };

    public static void main(String[] args) {
        cargarPokemons();
        cargarAltosMandos();
        cargarGimnasios();
        cargarHabitats();

        Scanner scanner = new Scanner(System.in);

        String opcion;
        do {
            System.out.println("1) Continuar");
            System.out.println("2) Nueva partida");
            System.out.println("3) Salir");

            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    menuContinuar();
                    break;
                case "2":
                    menuNuevaPartida();
                    break;
                case "3":
                    System.out.println("Saliendo del juego...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != "3");
        
    }

    private static void menuContinuar() {
        cargarRegistros();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        do {
            System.out.println("1) Revisar equipo.");
            System.out.println("2) Salir a capturar.");
            System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
            System.out.println("4) Retar un gimnasio.");
            System.out.println("5) Desafío al Alto Mando.");
            System.out.println("6) Curar Pokémon.");
            System.out.println("7) Guardar.");
            System.out.println("8) Guardar y Salir.");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    mostrarEquipoJugador();
                    break;
                case "2":
                    salirCapturar();
                    break;
                case "3":
                    accesoPC();
                    break;
                case "4":
                    retarGimnasio();
                    break;
                case "5":
                    desafiarAltoMando();
                    break;
                case "6":
                    curarPokemon();
                    break;
                case "7":
                    guardarPartida();
                    break;
                case "8":
                    guardarPartida();
                    System.out.println("Saliendo del juego...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != "8");
    }

    private static void menuNuevaPartida() {
        System.out.println("Ingrese su apodo de jugador:");
        Scanner scanner = new Scanner(System.in);
        String apodo = scanner.nextLine();
        apodoJugador = apodo;
        medallasJugador = 0;
        equipoJugador = new ArrayList<>();
        guardarPartida();
        menuContinuar();
    }

    private static void mostrarEquipoJugador() {
        if (equipoJugador.size() == 0) {
            System.out.println("No tienes pokemons en tu equipo.");
        } else {
            int tamañoEquipo;
            if (equipoJugador.size() < 6) {
                tamañoEquipo = equipoJugador.size();
            } else {
                tamañoEquipo = 6;
            }

            System.out.println("Equipo del jugador:");
            for (int i = 0; i < tamañoEquipo; i++) {
                System.out.println((i + 1) + ") " + equipoJugador.get(i).getNombre() + "|" + equipoJugador.get(i).getTipo() + "|Stats totales: " + equipoJugador.get(i).getStatsTotales());
            }
        }   
    }

    private static void salirCapturar() {
        System.out.println("¿Dónde deseas ir a explorar?");
        System.out.println("Zonas disponibles:");
        
        for (int i = 0; i < habitats.size(); i++) {
            System.out.println((i + 1) + ") " + habitats.get(i));
        }
        System.out.println((habitats.size() + 1) + ") Volver al menu.");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        Random random = new Random();

        double valorAleatorio = random.nextDouble();

        float sumaPorcentajes = 0;

        for (Pokemon pokemon: pokedex) {
            if (pokemon.getHabitat().equals(habitats.get(opcion - 1)) && valorAleatorio <= sumaPorcentajes + pokemon.getPorcentajeAparicion()) {
                System.out.println("Oh!! Ha aparecido un increible " + pokemon.getNombre() + " !!");
                System.out.println("Que deseas hacer?");

                System.out.println("1) Capturar");
                System.out.println("2) Huir");

                int opcionAccion = scanner.nextInt();

                if (opcionAccion == 1) {
                    boolean capturado = false;
                    for (Pokemon p: equipoJugador) {
                        if (p.getNombre().equals(pokemon.getNombre())) {
                            capturado = true;
                            break;
                        }
                    }

                    if (capturado) {
                        System.out.println("Ya tienes un " + pokemon.getNombre() + " en tu equipo! No puedes capturar otro.");
                    } else {
                        System.out.println(pokemon.getNombre() + " capturado con éxito!!");
                        equipoJugador.add(pokemon);
                    }
                }
                break;
            } else if (pokemon.getHabitat().equals(habitats.get(opcion - 1))) {
                sumaPorcentajes += pokemon.getPorcentajeAparicion();
            }
        }
    }

    private static void accesoPC() {
        if (equipoJugador.size() <= 6) {
            System.out.println("No tienes pokemons en la PC para intercambiar.");
        } else {
            System.out.println("Tu equipo actual:");
            for (int i = 0; i < 6; i++) {
                System.out.println((i + 1) + ") " + equipoJugador.get(i).getNombre() + " - Estado: " + equipoJugador.get(i).getEstado());
            }

            System.out.println("¿Qué Pokémon desea cambiar?");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                Pokemon pokemonSeleccionado = equipoJugador.get(opcion - 1);
                System.out.println("Pokémon seleccionado: " + pokemonSeleccionado.getNombre());

                System.out.println("Pokémon disponibles en la PC:");
                for (int i = 6; i < equipoJugador.size(); i++) {
                    System.out.println((i - 5) + ") " + equipoJugador.get(i).getNombre() + " - Estado: " + equipoJugador.get(i).getEstado());
                }
                System.out.println("¿Con cuál Pokémon deseas intercambiar?");
                int opcionIntercambio = scanner.nextInt();

                if (opcionIntercambio >= 1 && opcionIntercambio <= equipoJugador.size() - 6) {
                    Pokemon pokemonIntercambio = equipoJugador.get(opcionIntercambio + 5);

                    equipoJugador.set(opcion - 1, pokemonIntercambio);
                    equipoJugador.set(opcionIntercambio + 5, pokemonSeleccionado);
                    System.out.println("Intercambio realizado!");
                } else {
                    System.out.println("Opción de intercambio no válida. Volviendo al menú.");
                }

            
            } else {
                System.out.println("Opción no válida. Volviendo al menú.");
            }
        }

        
    }

    private static void retarGimnasio() {
        if (equipoJugador.size() == 0) {
            System.out.println("No tienes pokemons suficientesen tu equipo para retar un gimnasio.");
            return;
        }

        System.out.println("¿A cuál lider deseas retar?");
        for (int i = 0; i < gimnasios.size(); i++) {
            System.out.println((i + 1) + ") " + gimnasios.get(i).getLider() + " - Estado:" + gimnasios.get(i).getEstado());
        }
        System.out.println((gimnasios.size() + 1) + ") Volver al menu.");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        if (opcion == gimnasios.size() + 1) {
            System.out.println("Volviendo al menú...");
            return;
        }

        if (opcion > medallasJugador + 1) {
            System.out.println("Calmado Entrenador!!! No puedes retar a " + gimnasios.get(opcion - 1).getLider() + " sin haber derrotado a los lideres anteriores!!");
        } else if (gimnasios.get(opcion - 1).getEstado().equals("Derrotado")) {
            System.out.println("Ya has derrotado a " + gimnasios.get(opcion - 1).getLider() + ". No puedes retarlo de nuevo.");
        } else {
            System.out.println("Desafiando a " + gimnasios.get(opcion - 1).getLider() + "!!");
            int numPokemonGimnasio = 0;
            int pokemonsVivosJugador;
            Pokemon pokemonJugador = null;
            do {
                pokemonsVivosJugador = 0;
                int tamañoEquipo;
                if (equipoJugador.size() < 6) {
                    tamañoEquipo = equipoJugador.size();
                } else {
                    tamañoEquipo = 6;
                }
                for (int i = 0; i < tamañoEquipo; i++) {
                    if (equipoJugador.get(i).getEstado().equals("Vivo")) {
                        pokemonsVivosJugador++;
                    }
                }


                if (pokemonsVivosJugador == 0) {
                    System.out.println("Te has quedado sin pokemons en tu equipo! Volviendo al menu...");
                } else {
                    if (numPokemonGimnasio < gimnasios.get(opcion - 1).getCantidadPokemon()) {
                        Pokemon pokemonGimnasio = gimnasios.get(opcion - 1).getPokemons().get(numPokemonGimnasio);
                        if (pokemonJugador == null || pokemonJugador.getEstado().equals("Debilitado")) {
                            for (int i = 0; i < tamañoEquipo; i++) {
                                if (equipoJugador.get(i).getEstado().equals("Vivo")) {
                                    pokemonJugador = equipoJugador.get(i);
                                    break;
                                }
                            }
                        }
 
                        System.out.println(gimnasios.get(opcion - 1).getLider() + " saca a " + pokemonGimnasio.getNombre());
                        System.out.println(apodoJugador + " saca a " + pokemonJugador.getNombre());

                        System.out.println("Qué deseas hacer?");
                        System.out.println("1) Atacar");
                        System.out.println("2) Cambiar de pokemon");
                        System.out.println("3) Rendirse");

                        int opcionBatalla = scanner.nextInt();
                        switch (opcionBatalla) {
                            case 1:
                                System.out.println(pokemonJugador.getNombre() + " -> " + pokemonJugador.getStatsTotales());
                                System.out.println(pokemonGimnasio.getNombre() + " -> " + pokemonGimnasio.getStatsTotales());

                                double efectividad = EFECTIVIDAD[obtenerIndiceTipo(pokemonJugador.getTipo())][obtenerIndiceTipo(pokemonGimnasio.getTipo())];
                                double ataqueJugador = pokemonJugador.getStatsTotales() * efectividad;


                                if (efectividad > 1.0) {
                                    System.out.println(pokemonJugador.getNombre() + " es efectivo contra " + pokemonGimnasio.getNombre() + "!");
                                    System.out.println("Nuevo puntaje:");
                                    System.out.println(pokemonJugador.getNombre() + " -> " + ataqueJugador);
                                    System.out.println(pokemonGimnasio.getNombre() + " -> " + pokemonGimnasio.getStatsTotales());
                                } else if (efectividad < 1.0) {
                                    System.out.println(pokemonJugador.getNombre() + " no es efectivo contra " + pokemonGimnasio.getNombre() + "!");
                                    System.out.println("Nuevo puntaje:");
                                    System.out.println(pokemonJugador.getNombre() + " -> " + ataqueJugador);
                                    System.out.println(pokemonGimnasio.getNombre() + " -> " + pokemonGimnasio.getStatsTotales());
                                }

                                if (ataqueJugador >= pokemonGimnasio.getStatsTotales()) {
                                    System.out.println("Ha ganado " + pokemonJugador.getNombre() + "!" + pokemonGimnasio.getNombre() + " ha sido derrotado");
                                    if (numPokemonGimnasio == gimnasios.get(opcion - 1).getCantidadPokemon() - 1) {
                                        System.out.println("Felicidades! Has derrotado a " + gimnasios.get(opcion - 1).getLider() + " y obtenido la medalla " + opcion + "!!");
                                        gimnasios.get(opcion - 1).setEstado("Derrotado");
                                        medallasJugador++;
                                    }
                                    numPokemonGimnasio++;
                                } else {
                                    System.out.println("Ha ganado " + pokemonGimnasio.getNombre() + "!" + pokemonJugador.getNombre() + " ha sido derrotado");
                                    pokemonJugador.setEstado("Debilitado");
                                }
                                break;
                            case 2:
                                System.out.println("Selecciona el pokemon con el que deseas atacar:");
                                for (int i = 0; i < tamañoEquipo; i++) {
                                    System.out.println((i + 1) + ") " + equipoJugador.get(i).getNombre() + " - Estado: " + equipoJugador.get(i).getEstado());
                                
                                }
                                int opcionPokemon = scanner.nextInt();
                                while (opcionPokemon < 1 || opcionPokemon > tamañoEquipo || equipoJugador.get(opcionPokemon - 1).getEstado().equals("Debilitado")) {
                                    System.out.println("Opción no válida. Selecciona un pokemon vivo del equipo:");
                                    opcionPokemon = scanner.nextInt();
                                }
                                pokemonJugador = equipoJugador.get(opcionPokemon - 1);
                                break;
                            case 3:
                                System.out.println("Rindiéndose...");
                                return;
                            default:
                                System.out.println("Opción no válida. Intente de nuevo.");
                        }
                    }
                }

            } while (pokemonsVivosJugador > 0 && gimnasios.get(opcion - 1).getEstado().equals("Sin derrotar"));

        }
    }

    private static void desafiarAltoMando() {
        if (equipoJugador.size() == 0) {
            System.out.println("No tienes pokemons en tu equipo para desafiar al Alto Mando.");
            return;
        }
        if (medallasJugador < gimnasios.size()) {
            System.out.println("Calmado Entrenador! No puedes desafiar al Alto Mando sin haber derrotado a todos los gimnasios");
            return;
        }
        System.out.println("¿A cuál Alto Mando deseas retar?");
        for (int i = 0; i < altosMandos.size(); i++) {
            System.out.println((i + 1) + ") " + altosMandos.get(i).getNombre());
        }
        System.out.println((altosMandos.size() + 1) + ") Volver al menu.");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        if (opcion == altosMandos.size() + 1) {
            return;
        }

        System.out.println("Desafiando a " + altosMandos.get(opcion - 1).getNombre() + "!!");
        int numPokemonAltoMando = 0;
        int pokemonsVivosAltoMando = 6;
        int pokemonsVivosJugador;
        Pokemon pokemonJugador = null;

        do {
            pokemonsVivosJugador = 0;
            int tamañoEquipo;
            if (equipoJugador.size() < 6) {
                tamañoEquipo = equipoJugador.size();
            } else {
                tamañoEquipo = 6;
            }
            for (int i = 0; i < tamañoEquipo; i++) {
                if (equipoJugador.get(i).getEstado().equals("Vivo")) {
                    pokemonsVivosJugador++;
                }
            }

            if (pokemonsVivosJugador == 0) {
                System.out.println("Te has quedado sin pokemons en tu equipo! Volviendo al menu...");
            } else {
                if (pokemonsVivosAltoMando > 0) {
                    Pokemon pokemonAltoMando = altosMandos.get(opcion - 1).getPokemons().get(numPokemonAltoMando);
                    
                    if (pokemonJugador == null || pokemonJugador.getEstado().equals("Debilitado")) {
                        for (int i = 0; i < tamañoEquipo; i++) {
                            if (equipoJugador.get(i).getEstado().equals("Vivo")) {
                                pokemonJugador = equipoJugador.get(i);
                                break;
                            }
                        }
                    }

                    System.out.println(altosMandos.get(opcion - 1).getNombre() + " saca a " + pokemonAltoMando.getNombre());
                    System.out.println(apodoJugador + " saca a " + pokemonJugador.getNombre());

                    System.out.println("Qué deseas hacer?");
                    System.out.println("1) Atacar");
                    System.out.println("2) Cambiar de pokemon");
                    System.out.println("3) Rendirse");

                    int opcionBatalla = scanner.nextInt();
                    switch (opcionBatalla) {
                        case 1:
                            System.out.println(pokemonJugador.getNombre() + " -> " + pokemonJugador.getStatsTotales());
                            System.out.println(pokemonAltoMando.getNombre() + " -> " + pokemonAltoMando.getStatsTotales());

                            double efectividad = EFECTIVIDAD[obtenerIndiceTipo(pokemonJugador.getTipo())][obtenerIndiceTipo(pokemonAltoMando.getTipo())];
                            double ataqueJugador = pokemonJugador.getStatsTotales() * efectividad;


                            if (efectividad > 1.0) {
                                System.out.println(pokemonJugador.getNombre() + " es efectivo contra " + pokemonAltoMando.getNombre() + "!");
                                System.out.println("Nuevo puntaje:");
                                System.out.println(pokemonJugador.getNombre() + " -> " + ataqueJugador);
                                System.out.println(pokemonAltoMando.getNombre() + " -> " + pokemonAltoMando.getStatsTotales());
                            } else if (efectividad < 1.0) {
                                System.out.println(pokemonJugador.getNombre() + " no es efectivo contra " + pokemonAltoMando.getNombre() + "!");
                                System.out.println("Nuevo puntaje:");
                                System.out.println(pokemonJugador.getNombre() + " -> " + ataqueJugador);
                                System.out.println(pokemonAltoMando.getNombre() + " -> " + pokemonAltoMando.getStatsTotales());
                            }

                            if (ataqueJugador >= pokemonAltoMando.getStatsTotales()) {
                                System.out.println("Ha ganado " + pokemonJugador.getNombre() + "!" + pokemonAltoMando.getNombre() + " ha sido derrotado");
                                pokemonsVivosAltoMando--;
                                
                                if (pokemonsVivosAltoMando == 0) {
                                    System.out.println("Felicidades! Has derrotado a " + altosMandos.get(opcion - 1).getNombre() + "!!");
                                } else {
                                    numPokemonAltoMando++;
                                }
                            } else {
                                System.out.println("Ha ganado " + pokemonAltoMando.getNombre() + "!" + pokemonJugador.getNombre() + " ha sido derrotado");
                                pokemonJugador.setEstado("Debilitado");
                            }
                            break;
                        case 2:
                            System.out.println("Selecciona el pokemon con el que deseas atacar:");
                            for (int i = 0; i < tamañoEquipo; i++) {
                                System.out.println((i + 1) + ") " + equipoJugador.get(i).getNombre() + " - Estado: " + equipoJugador.get(i).getEstado());            
                            }
                            int opcionPokemon = scanner.nextInt();
                            while (opcionPokemon < 1 || opcionPokemon > tamañoEquipo || equipoJugador.get(opcionPokemon - 1).getEstado().equals("Debilitado")) {
                                System.out.println("Opción no válida. Selecciona un pokemon vivo del equipo:");
                                opcionPokemon = scanner.nextInt();
                            }
                            pokemonJugador = equipoJugador.get(opcionPokemon - 1);
                            break;
                        case 3:
                            System.out.println("Rindiéndose...");
                            return;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
            }

        } while (pokemonsVivosJugador > 0 && pokemonsVivosAltoMando > 0);
    }

    private static void curarPokemon() {
        if (equipoJugador.size() == 0) {
            System.out.println("No tienes pokemons en tu equipo para curar.");
            return;
        }
        int tamañoEquipo;
        if (equipoJugador.size() < 6) {
            tamañoEquipo = equipoJugador.size();
        } else {
            tamañoEquipo = 6;
        }
        for (int i = 0; i < tamañoEquipo; i++) {
            equipoJugador.get(i).setEstado("Vivo");
        }
        System.out.println("Tu equipo se ha recuperado!");
    }

    private static void guardarPartida() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Registros.txt"));

            writer.write(apodoJugador + ";" + medallasJugador);
            writer.newLine();

            for (Pokemon pokemon: equipoJugador) {
                writer.write(pokemon.getNombre() + ";" + pokemon.getEstado());
                writer.newLine();
            }

            writer.close();
            System.out.println("Partida guardada!");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida.");
        }
    }

    private static void cargarPokemons() {
        try {
            Scanner scanner = new Scanner(new File("Pokedex.txt"));

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");

                String nombre = datos[0];
                String habitat = datos[1];
                float porcentajeAparicion = Float.parseFloat(datos[2]);
                int vida = Integer.parseInt(datos[3]);
                int ataque = Integer.parseInt(datos[4]);
                int defensa = Integer.parseInt(datos[5]);
                int ataqueEspecial = Integer.parseInt(datos[6]);
                int defensaEspecial = Integer.parseInt(datos[7]);
                int velocidad = Integer.parseInt(datos[8]);
                String tipo = datos[9];

                Pokemon pokemon = new Pokemon(nombre, habitat, porcentajeAparicion, vida, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, tipo);
                pokedex.add(pokemon);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de pokemons.");
        }
    }

    private static void cargarGimnasios() {
        try {
            Scanner scanner = new Scanner(new File("Gimnasios.txt"));
            

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");

                int numeroGimnasio = Integer.parseInt(datos[0]);
                String lider = datos[1];
                String estado = datos[2];
                int cantidadPokemon = Integer.parseInt(datos[3]);
                ArrayList<Pokemon> pokemons = new ArrayList<>();

                for (int i = 0; i < cantidadPokemon; i++){
                    String nombrePokemon = datos[4 + i];
                    for (Pokemon pokemon: pokedex) {
                        if (pokemon.getNombre().equals(nombrePokemon)) {
                            pokemons.add(pokemon);
                            break;
                        }
                    }
                }

                Gimnasio gimnasio = new Gimnasio(numeroGimnasio, lider, estado, cantidadPokemon, pokemons);
                gimnasios.add(gimnasio);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de gimnasios.");
        }
    }

    private static void cargarAltosMandos() {
        try {
            Scanner scanner = new Scanner(new File("Alto Mando.txt"));

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");

                int numeroAltoMando= Integer.parseInt(datos[0]); 
                String nombre = datos[1];
                ArrayList<Pokemon> pokemons = new ArrayList<>();

                for (int i = 0; i < 6; i++){
                    String nombrePokemon = datos[2 + i];
                    for (Pokemon pokemon: pokedex) {
                        if (pokemon.getNombre().equals(nombrePokemon)) {
                            pokemons.add(pokemon);
                            break;
                        }
                    }
                }
                AltoMando altoMando = new AltoMando(numeroAltoMando, nombre, pokemons);
                altosMandos.add(altoMando);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de altos mandos.");
        }
    }

    private static void cargarHabitats() {
        try {
            Scanner scanner = new Scanner(new File("Habitats.txt"));

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                habitats.add(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de habitats.");
        }
    }

    private static void cargarRegistros(){
        try {
            equipoJugador = new ArrayList<>();
            Scanner scanner = new Scanner(new File("Registros.txt"));
            
            int i = 0;
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");

                if (i == 0) {
                    apodoJugador = datos[0];
                    medallasJugador = Integer.parseInt(datos[1]);

                    for (int j = 0; j < gimnasios.size(); j++) {
                        if (medallasJugador > j) {
                            gimnasios.get(j).setEstado("Derrotado");
                        }
                    }
                } else {
                    String nombrePokemon = datos[0];
                    String estado = datos[1];
                    for (Pokemon pokemon: pokedex) {
                        if (pokemon.getNombre().equals(nombrePokemon)) {
                            pokemon.setEstado(estado);
                            equipoJugador.add(pokemon);
                            break;
                        }
                    }
                } 
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de registros.");
        }
    }

    private static int obtenerIndiceTipo(String tipo) {
        for (int i = 0; i < TIPOS.length; i++) {
            if (TIPOS[i].equals(tipo)) {
                return i;
            }
        }
        return -1;
    }

}