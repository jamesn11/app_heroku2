package br.gov.etec.app.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CadastroCursoDtoOld implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -65335596300406677L;
	
	private Long id;
	private String nome;
	private String descricao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "nome não pode ser vazio.")
	@Length(min = 5, max = 200, message = "nome deve conter entre 5 e 200 caracteres.")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "CadastroCursoDto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	
	
}
