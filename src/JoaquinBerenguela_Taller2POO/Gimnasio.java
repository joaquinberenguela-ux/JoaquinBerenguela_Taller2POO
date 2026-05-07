package JoaquinBerenguela_Taller2POO;

import java.util.ArrayList;

public class Gimnasio {
	private int numeroGimnasio;
	private String lider;
	private String estado;
	private int cantidadPokemon;
	private ArrayList<Pokemon> pokemons;

	public Gimnasio(int numeroGimnasio, String lider, String estado, int cantidadPokemon, ArrayList<Pokemon> pokemons) {
		this.numeroGimnasio = numeroGimnasio;
		this.lider = lider;
		this.estado = estado;
		this.cantidadPokemon = cantidadPokemon;
		this.pokemons = pokemons;
	}

	public int getNumeroGimnasio() {
		return numeroGimnasio;
	}

	public void setNumeroGimnasio(int numeroGimnasio) {
		this.numeroGimnasio = numeroGimnasio;
	}

	public String getLider() {
		return lider;
	}

	public void setLider(String lider) {
		this.lider = lider;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCantidadPokemon() {
		return cantidadPokemon;
	}

	public void setCantidadPokemon(int cantidadPokemon) {
		this.cantidadPokemon = cantidadPokemon;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(ArrayList<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}

}
