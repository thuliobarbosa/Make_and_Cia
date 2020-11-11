package br.com.libertas.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import com.google.gson.JsonObject;

import br.com.libertas.dto.Usuario;
import br.com.libertas.dto.VendasProduto;

public class VendasProdutoDao {
	
	/***
	 * @param v dados de um produto
	 * @return se foi possivel fazer o cadastro
	 */
	public JsonObject inserir(VendasProduto v) {
			
			boolean cond = true;
			String saida = "Sem retorno!";
			JsonObject resposta = new JsonObject();
			Conexao con = new Conexao();
			
			try {
				String sql = "INSERT INTO vendasProduto (pedido, produto, pre�o) VALUES"
						+ "(?,?,?)";
				
				PreparedStatement prep = con.getConexao().prepareStatement(sql);
				prep.setInt(1, v.getPedido());
				prep.setInt(2, v.getProduto());
				prep.setDouble(3, v.getPre�o());
				prep.execute();
				
				saida = "Produto da venda cadastrado com sucesso!";
				
			} catch (Exception e) {
				e.printStackTrace();
				saida = "N�o foi possivel cadastrar o produto da venda, motivo : " + e.toString();
				cond = false;
			}
			con.desconecta();
			
			resposta.addProperty("Condi��o: ", cond);
			resposta.addProperty("Mensagem:" , saida);
			
			return resposta;
			
		}
	
	
	/**
	 * @param id
	 * @return
	 */
	public JsonObject excluir(int id) {
			
			boolean cond = true;
			String saida = "Sem retorno!";
			JsonObject resposta = new JsonObject();
			Conexao con = new Conexao();
			
			try {
				
				String sql = "DELETE FROM vendasProduto WHERE id_VendasProdutos  = ?";
				PreparedStatement prep = con.getConexao().prepareStatement(sql);
				prep.setInt(1, id);
				prep.execute();
				
				saida = "Produto da venda exclu�do com sucesso!";
				
			} catch (Exception e) {
				e.printStackTrace();
				saida = "N�o foi poss�vel exlcuir o produto da venda, motivo: " + e.toString();
				cond = false;
			}
			con.desconecta();
			
			resposta.addProperty("Condi��o ", cond);
			resposta.addProperty("Mensagem ", saida);
			
			return resposta;
		}
	/**
	 * @param v
	 * @return
	 */
	public JsonObject alterar(VendasProduto v) {
		boolean cond = true;
		String mensagem = "Sem retorno!";
		JsonObject resposta = new JsonObject();
		Conexao con = new Conexao();
		
		try {
			
			String sql = "UPDATE vendasProduto SET pedido=?, produto=?, pre�o=? WHERE id_VendasProdutos =?";
			
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setInt(1, v.getPedido());
			prep.setInt(2, v.getProduto());
			prep.setDouble(3, v.getPre�o());
			prep.setInt(4, v.getId_VendaProdutos());
		
			prep.execute();
			
			mensagem = "Produto da venda atualizado com sucesso!";
			
		} catch (Exception e) {
			e.printStackTrace();
			mensagem = "N�o foi poss�vel atualizar o produto da venda, motivo: "+ e.toString();
			cond = false;
		}
		
		con.desconecta();
		
		resposta.addProperty("Condi��o ", cond);
		resposta.addProperty("Mensagem" , mensagem);
		
		return resposta;
	}
	
	
	/**
	 * @param id
	 * @return
	 */
	public VendasProduto consultar(int id) {
			
		VendasProduto v = new VendasProduto();
			Conexao con = new Conexao();
			
			System.out.print("Parametro => " + id);
			
			try {
				
				String sql = "SELECT * FROM vendasProduto WHERE id_VendasProdutos  = " + id;
				
				Statement sta = con.getConexao().createStatement();
				ResultSet res = sta.executeQuery(sql);
				
				if (res.next()) {
					v.setId_VendaProdutos(res.getInt("id_VendasProdutos"));
					v.setPedido(res.getInt("pedido"));
					v.setProduto(res.getInt("produto"));
					v.setPre�o(res.getDouble("pre�o"));
				}
				
				res.close();
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			con.desconecta();
			
			return v;
		}
	
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<VendasProduto> listar(){
			
			Conexao con = new Conexao();
			LinkedList<VendasProduto> listaVendasProduto = new LinkedList<VendasProduto>();
			
			try {
				
				String sql = "SELECT * FROM cad_usuario";
				
				Statement sta = con.getConexao().createStatement();
				ResultSet res = sta.executeQuery(sql);
				
				while (res.next()) {
					VendasProduto v = new VendasProduto();
					v.setId_VendaProdutos(res.getInt("id_VendasProdutos"));
					v.setPedido(res.getInt("pedido"));
					v.setProduto(res.getInt("produto"));
					v.setPre�o(res.getDouble("pre�o"));
					listaVendasProduto.add(v);
					
				}
				res.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			con.desconecta();
			
			return listaVendasProduto;
		}
}
