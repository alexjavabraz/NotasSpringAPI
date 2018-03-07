package seguros.api.domain;

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

	@Column(name = "dh_transacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTransacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_conta_origem")	
	private Conta origem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_conta_destino")
	private Conta destino;	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario_inclusao")
	private Usuario usuarioInclusao;		
	
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

	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}
	

}
