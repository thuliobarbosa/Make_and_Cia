package br.com.libertas.dto;

public class VendasProduto {
	private int id_VendaProdutos;
	private int pedido;
	private int produto;
	private double pre�o;
	public int getId_VendaProdutos() {
		return id_VendaProdutos;
	}
	public void setId_VendaProdutos(int id_VendaProdutos) {
		this.id_VendaProdutos = id_VendaProdutos;
	}
	public int getPedido() {
		return pedido;
	}
	public void setPedido(int pedido) {
		this.pedido = pedido;
	}
	public int getProduto() {
		return produto;
	}
	public void setProduto(int produto) {
		this.produto = produto;
	}
	public double getPre�o() {
		return pre�o;
	}
	public void setPre�o(double pre�o) {
		this.pre�o = pre�o;
	}
	
}
