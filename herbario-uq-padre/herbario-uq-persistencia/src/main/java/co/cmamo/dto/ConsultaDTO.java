package co.cmamo.dto;

import java.util.List;

import co.cmamo.*;

public class ConsultaDTO {
	private String id;
	private long cantRegistros;
	
	public ConsultaDTO(String id, Object registros_raw) {
		@SuppressWarnings("unchecked")
		List<Peticion> registros = (List<Peticion>) registros_raw;
		this.cantRegistros = registros == null ? 0 : registros.size();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCantRegistros() {
		return cantRegistros;
	}

	public void setCantRegistros(long cantRegistros) {
		this.cantRegistros = cantRegistros;
	}
}
