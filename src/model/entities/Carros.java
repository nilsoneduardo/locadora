package model.entities;

public class Carros {
	
	private int id;
	private String modelo;

	public Carros(int id, String modelo) {

		this.id = id;
		this.modelo = modelo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return modelo;
	}

}
