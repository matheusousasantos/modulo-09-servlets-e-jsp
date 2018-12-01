package beans;

import java.util.Date;

public class Conta {
	
	private Long id;
	private Float valor;
	private Date dtInicio;
	private int qtdParcela;
	private String observacao;
	private int pslPaga;
	private Float valorTotal;
	private int status;
	private Usuario usuario;
	private Salario salario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public int getQtdParcela() {
		return qtdParcela;
	}
	public void setQtdParcela(int qtdParcela) {
		this.qtdParcela = qtdParcela;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public int getPslPaga() {
		return pslPaga;
	}
	public void setPslPaga(int pslPaga) {
		this.pslPaga = pslPaga;
	}
	public Float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Salario getSalario() {
		return salario;
	}
	public void setSalario(Salario salario) {
		this.salario = salario;
	}
}
