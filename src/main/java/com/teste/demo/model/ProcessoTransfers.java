package com.teste.demo.model;

import java.time.LocalDate;
import java.util.Date;

public class ProcessoTransfers {
	private Long id;
	private String nome;	
	
	private String cpf;
	
	private String matricula;	
	
	private String orgao;
	
	private String setor;
	
	private String beneficio;
	
	private Date dataTramitacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public Date getDataTramitacao() {
		return dataTramitacao;
	}
	public void setDataTramitacao(Date dataTramitacao) {
		this.dataTramitacao = dataTramitacao;
	}
	public String getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}	
	
}
