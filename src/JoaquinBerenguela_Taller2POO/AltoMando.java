package JoaquinBerenguela_Taller2POO;

import java.util.ArrayList;

public class AltoMando {
	
	private int numeroAltoMando;
	private String nombre;
	private ArrayList<Pokemon> pokemons;

	public AltoMando(int numeroAltoMando, String nombre, ArrayList<Pokemon> pokemons) {
		this.numeroAltoMando = numeroAltoMando;
		this.nombre = nombre;
		this.pokemons = pokemons;
	}

	public int getNumeroAltoMando() {
		return numeroAltoMando;
	}

	public void setNumeroAltoMando(int numeroAltoMando) {
		this.numeroAltoMando = numeroAltoMando;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(ArrayList<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}
}