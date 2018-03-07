package seguros.api.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.DoubleSerializer;

import seguros.api.domain.Transacao;

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
	
	public TransacaoDTO(Transacao t){
		this.setId(t.getId());
		this.setDataTransacao(getFormatedDate(t.getDataTransacao()));
		this.setOrigem(t.getOrigem().getNome());
		this.setValor(t.getVlrValor().doubleValue());
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

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}



	private Long id;

	private String dataTransacao;
	
	private String origem;
	
	private Double valor;
	
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private String idUsuario;

	
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
