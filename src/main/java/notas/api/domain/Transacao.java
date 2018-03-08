package notas.api.domain;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.DoubleSerializer;

@Entity
@Table(name = "transacao")
public class Transacao  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2086719031217218783L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transacao")
	private Long id;
	
	@Column(name = "qtd_notas_100", nullable=true, length=10)
	private Integer qtdNotasCem;	
	
	@Column(name = "qtd_notas_50", nullable=true, length=10)
	private Integer qtdNotasCinquenta;
	
	@Column(name = "qtd_notas_20", nullable=true, length=10)
	private Integer qtdNotasVinte;
	
	@Column(name = "qtd_notas_10", nullable=true, length=10)
	private Integer qtdNotasDez;

	@Column(name = "dh_transacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTransacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_conta_origem")	
	private Conta origem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_conta_destino")
	private Conta destino;	

	@Column(name = "vlr_valor", nullable = false)
	private BigDecimal vlrValor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Conta getOrigem() {
		return origem;
	}

	public void setOrigem(Conta origem) {
		this.origem = origem;
	}

	public Conta getDestino() {
		return destino;
	}

	public void setDestino(Conta destino) {
		this.destino = destino;
	}


	@Transient
	public String getDataFormatada() {
		try{
		return "01/01/2019";
		}catch(Exception e){
			return "";
		}
	}

	@JsonSerialize(using=DoubleSerializer.class)
	@JsonProperty("vlrValor")
	public BigDecimal getVlrValor() {
		return vlrValor;
	}

	public void setVlrValor(BigDecimal vlrValor) {
		this.vlrValor = vlrValor;
	}

	public Integer getQtdNotasCem() {
		return qtdNotasCem;
	}

	public void setQtdNotasCem(Integer qtdNotasCem) {
		this.qtdNotasCem = qtdNotasCem;
	}

	public Integer getQtdNotasCinquenta() {
		return qtdNotasCinquenta;
	}

	public void setQtdNotasCinquenta(Integer qtdNotasCinquenta) {
		this.qtdNotasCinquenta = qtdNotasCinquenta;
	}

	public Integer getQtdNotasVinte() {
		return qtdNotasVinte;
	}

	public void setQtdNotasVinte(Integer qtdNotasVinte) {
		this.qtdNotasVinte = qtdNotasVinte;
	}

	public Integer getQtdNotasDez() {
		return qtdNotasDez;
	}

	public void setQtdNotasDez(Integer qtdNotasDez) {
		this.qtdNotasDez = qtdNotasDez;
	}
	

}
