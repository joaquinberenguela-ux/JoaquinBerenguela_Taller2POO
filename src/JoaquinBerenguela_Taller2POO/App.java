package JoaquinBerenguela_Taller2POO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

	private static ArrayList<Pokemon> pokedex = new ArrayList<>();
	private static ArrayList<Gimnasio> gimnasios = new ArrayList<>();
	private static ArrayList<AltoMando> altosMandos = new ArrayList<>();

	public static void main(String[] args) {
		cargarPokemons();
		cargarGimnasios();
		cargarAltosMandos();
 
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
		System.out.println("1) Revisar equipo.");
		System.out.println("2) Salir a capturar.");
		System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
		System.out.println("4) Retar un gimnasio.");
		System.out.println("5) Desafío al Alto Mando.");
		System.out.println("6) Curar Pokémon.");
		System.out.println("7) Guardar.");
		System.out.println("8) Guardar y Salir.");

		Scanner scanner = new Scanner(System.in);
		String opcion;

		do {
			opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				System.out.println("Revisar equipo.");
				break;
			case "2":
				System.out.println("Salir a capturar.");
				break;
			case "3":
				System.out.println("Acceso al PC (cambiar Pokémon del equipo).");
				break;
			case "4":
				System.out.println("Retar un gimnasio.");
				break;
			case "5":
				System.out.println("Desafiar al Alto Mando.");
				break;
			case "6":
				System.out.println("Curar Pokémon.");
				break;
			case "7":
				System.out.println("Guardar.");
				break;
			case "8":
				System.out.println("Guardar partida y Salir.");
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
		menuContinuar();
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

				Pokemon pokemon = new Pokemon(nombre, habitat, porcentajeAparicion, vida, ataque, defensa,
						ataqueEspecial, defensaEspecial, velocidad, tipo);
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

				for (int i = 0; i < cantidadPokemon; i++) {
					String nombrePokemon = datos[4 + i];
					for (Pokemon pokemon : pokedex) {
						if (pokemon.getNombre().equals(nombrePokemon)) {
							pokemons.add(pokemon);
							break;
						}
					}
				}
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

				int numeroAltoMando = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				ArrayList<Pokemon> pokemons = new ArrayList<>();

				for (int i = 0; i < 6; i++) {
					String nombrePokemon = datos[2 + i];
					for (Pokemon pokemon : pokedex) {
						if (pokemon.getNombre().equals(nombrePokemon)) {
							pokemons.add(pokemon);
							break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el archivo de altos mandos.");
		}
	}

}
