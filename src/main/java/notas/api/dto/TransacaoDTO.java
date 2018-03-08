package notas.api.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.DoubleSerializer;

import notas.api.domain.Transacao;

/**
 * 
 * @author alex.braz
 *
 */
public class TransacaoDTO implements Serializable{
	
	public TransacaoDTO(){}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 981684797813806867L;
	

	private Long id;

	private String dataTransacao;
	
	private String origem;
	
	private Double valor;
	
	private String mensagem = "";
	
	private int qtdNotasCem;	
	
	private int qtdNotasCinquenta;
	
	private int qtdNotasVinte;
	
	private int qtdNotasDez;	
	
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
	
	public TransacaoDTO(Transacao t){
		this.setId(t.getId());
		this.setDataTransacao(getFormatedDate(t.getDataTransacao()));
		this.setOrigem(t.getOrigem().getNome());
		this.setValor(t.getVlrValor().doubleValue());
		
		if(t.getQtdNotasCem() != null)
			this.setQtdNotasCem(t.getQtdNotasCem());
		
		if(t.getQtdNotasCinquenta() != null)
			this.setQtdNotasCinquenta(t.getQtdNotasCinquenta());
		
		if(t.getQtdNotasVinte() != null)
			this.setQtdNotasVinte(t.getQtdNotasVinte());
		
		if(t.getQtdNotasDez() != null)
			this.setQtdNotasDez(t.getQtdNotasDez());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	@JsonSerialize(using=DoubleSerializer.class)
	@JsonProperty("valor")	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public int getQtdNotasCem() {
		return qtdNotasCem;
	}

	public void setQtdNotasCem(int qtdNotasCem) {
		this.qtdNotasCem = qtdNotasCem;
	}

	public int getQtdNotasCinquenta() {
		return qtdNotasCinquenta;
	}

	public void setQtdNotasCinquenta(int qtdNotasCinquenta) {
		this.qtdNotasCinquenta = qtdNotasCinquenta;
	}

	public int getQtdNotasVinte() {
		return qtdNotasVinte;
	}

	public void setQtdNotasVinte(int qtdNotasVinte) {
		this.qtdNotasVinte = qtdNotasVinte;
	}

	public int getQtdNotasDez() {
		return qtdNotasDez;
	}

	public void setQtdNotasDez(int qtdNotasDez) {
		this.qtdNotasDez = qtdNotasDez;
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String getFormatedDate(Date time) {

		try {
			
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-02:00"));
			
			return simpleDateFormat.format(time);
			
		} catch (Exception e) {
			return "*Data inválida*";
		}

	}

}
