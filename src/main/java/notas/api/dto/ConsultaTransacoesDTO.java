package notas.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;

public class ConsultaTransacoesDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2291329610167813128L;
	private List<TransacaoDTO> itens = new ArrayList<TransacaoDTO>();

	@JsonSerialize(typing = Typing.STATIC)
	public List<TransacaoDTO> getItens() {
		return itens;
	}

	public void setItens(List<TransacaoDTO> itens) {
		this.itens = itens;
	}
	
	

}
