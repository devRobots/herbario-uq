package co.cmamo.dto;

import java.util.List;

import co.cmamo.Peticion;

public class ConsultaDTO {
	private String id;
	private int cantRegistros;
	
	public ConsultaDTO() {
		id = ":v";
		cantRegistros = 0;
	}
	
	public ConsultaDTO(String id, List<Peticion> peticiones) {
		cantRegistros = peticiones.isEmpty()? 0 : peticiones.size();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCantRegistros() {
		return cantRegistros;
	}

	public void setCantRegistros(int cantRegistros) {
		this.cantRegistros = cantRegistros;
	}
}
