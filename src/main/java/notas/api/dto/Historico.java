package notas.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;

/**
 * Esta classe contem o historico de transacoes de uma conta
 * 
 * @author alex.braz
 *
 */
public class Historico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1084676079049057420L;
	private String conta;
	private List<TransacaoDTO> transacoes;

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@JsonSerialize(typing = Typing.STATIC)
	public List<TransacaoDTO> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<TransacaoDTO> transacoes) {
		this.transacoes = transacoes;
	}

}
