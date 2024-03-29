package br.com.strella.srv.empresa.adapter.output.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empresa")
public class EmpresaOutputDTO implements Serializable {
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@JsonProperty("urlAcesso")
	@Column(name = "url_acesso")
	private String urlAcesso;
	@JsonProperty("usuUltAlteracao")
	@Column(name = "usu_ult_alteracao")
	private String usuUltAlteracao;
	@JsonProperty("dataAtualizacao")
	@Column(name = "data_atualizacao")
	private LocalDate dataAtualizacao;
	@JsonProperty("idPlanoEmpresa")
	@Column(name = "id_plano_empresa")
	private Long idPlanoEmpresa;
	@JsonProperty("idLogo")
	@Column(name = "id_logo")
	private Long idLogo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrlAcesso() {
		return urlAcesso;
	}

	public void setUrlAcesso(String urlAcesso) {
		this.urlAcesso = urlAcesso;
	}

	public String getUsuUltAlteracao() {
		return usuUltAlteracao;
	}

	public void setUsuUltAlteracao(String usuUltAlteracao) {
		this.usuUltAlteracao = usuUltAlteracao;
	}

	public LocalDate getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDate dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Long getIdPlanoEmpresa() {
		return idPlanoEmpresa;
	}

	public void setIdPlanoEmpresa(Long idPlanoEmpresa) {
		this.idPlanoEmpresa = idPlanoEmpresa;
	}

	public Long getIdLogo() {
		return idLogo;
	}

	public void setIdLogo(Long idLogo) {
		this.idLogo = idLogo;
	}

	public EmpresaOutputDTO(Long id, String urlAcesso, String usuUltAlteracao,
		LocalDate dataAtualizacao, Long idPlanoEmpresa, Long idLogo) {
		this.id = id;
		this.urlAcesso = urlAcesso;
		this.usuUltAlteracao = usuUltAlteracao;
		this.dataAtualizacao = dataAtualizacao;
		this.idPlanoEmpresa = idPlanoEmpresa;
		this.idLogo = idLogo;
	}

	public EmpresaOutputDTO() {}
}