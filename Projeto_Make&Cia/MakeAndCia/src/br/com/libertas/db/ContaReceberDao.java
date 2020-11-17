package br.com.libertas.db;

import java.sql.PreparedStatement;
import java.util.LinkedList;

import com.google.gson.JsonObject;

import br.com.libertas.dto.ContasReceber;

public class ContaReceberDao {



public JsonObject inserir(ContasReceber cr) {
	
	boolean cond = true;
	String saida = "Sem retorno!";
	JsonObject resposta = new JsonObject();
	Conexao con = new Conexao();
	
	try {
		String sql = "INSERT INTO cad_contaReceber(descricao, valor, data, status, parcela) VALUES"
				+ "(?,?,?,?,?)";
		
		PreparedStatement prep = con.getConexao().prepareStatement(sql);
		prep.setString(1, cr.getDescricao());
		prep.setDouble(2, cr.getValor());
		prep.setDate(3, cr.getData());
		prep.setString(4, cr.getStatus());
		prep.setInt(5, cr.getParcela());
		prep.execute();
		
		saida = "Conta a receber cadastrada com sucesso!";
		
	} catch (Exception e) {
		e.printStackTrace();
		saida = "Não foi possivel cadastrar a conta a receber, motivo : " + e.toString();
		cond = false;
	}
	con.desconecta();
	
	resposta.addProperty("Condição", cond);
	resposta.addProperty("Mensagem" , saida);
	
	return resposta;
	
}

public JsonObject excluir(int id) {
	
	boolean cond = true;
	String saida = "Sem retorno!";
	JsonObject resposta = new JsonObject();
	Conexao con = new Conexao();
	
	try {
		
		String sql = "DELETE FROM cad_contaReceber WHERE id = ?";
		PreparedStatement prep = con.getConexao().prepareStatement(sql);
		prep.setInt(1, id);
		prep.execute();
		
		saida = "Conta a receber excluída com sucesso!";
		
	} catch (Exception e) {
		e.printStackTrace();
		saida = "Não foi possível exlcuir a conta a receber, motivo: " + e.toString();
		cond = false;
	}
	con.desconecta();
	
	resposta.addProperty("Condição", cond);
	resposta.addProperty("Mensagem", saida);
	
	return resposta;
}


public JsonObject alterar(ContasReceber cr) {
	
	boolean cond = true;
	String mensagem = "Sem retorno!";
	JsonObject resposta = new JsonObject();
	Conexao con = new Conexao();
	
	try {
		
		String sql = "UPDATE cad_contaReceber SET descricao=?, valor=?, data=?, status=?, parcela=? WHERE id=?";
		
		PreparedStatement prep = con.getConexao().prepareStatement(sql);
		prep.setString(1, cr.getDescricao());
		prep.setDouble(2, cr.getValor());
		prep.setDate(3, cr.getData());
		prep.setString(4, cr.getStatus());
		prep.setInt(5, cr.getParcela());
		prep.setInt(6, cr.getId());
		prep.execute();
		
		mensagem = "Conta a receber atualizada com sucesso!";
		
	} catch (Exception e) {
		e.printStackTrace();
		mensagem = "Nao foi possível atualizar a conta a receber, motivo: "+ e.toString();
		cond = false;
	}
	
	con.desconecta();
	
	resposta.addProperty("Condição", cond);
	resposta.addProperty("Mensagem" , mensagem);
	
	return resposta;
}


public ContasReceber consultar(int id) {
	
	ContasReceber cr = new ContasReceber();
	Conexao con = new Conexao();
	
	System.out.print("Parametro => " + id);
	
	try {
		
		String sql = "SELECT * FROM cad_contaReceber WHERE id = " + id;
		
		Statement sta = con.getConexao().createStatement();
		ResultSet res = sta.executeQuery(sql);
		
		if (res.next()) {
			cr.setDescricao(res.getString("descricao"));
			cr.setValor(Double.parseDouble(res.getString("valor")));
			cr.setData(res.getDate("data"));
			cr.setStatus(res.getString("status"));
			cr.setParcela(Integer.parseInt(res.getString("parcela")));
			cr.setId(res.getInt("id"));
		}
		
		res.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	con.desconecta();
	
	return cr;
}

public LinkedList<ContasReceber> listar(){
	
	Conexao con = new Conexao();
	LinkedList<ContasReceber> listaContasReceber = new LinkedList<ContasReceber>();
	
	try {
		
		String sql = "SELECT * FROM cad_contaPagar";
		
		Statement sta = con.getConexao().createStatement();
		ResultSet res = sta.executeQuery(sql);
		
		while (res.next()) {
			ContasReceber cr = new ContasReceber();
			cr.setDescricao(res.getString("descricao"));
			cr.setValor(Double.parseDouble(res.getString("valor")));
			cr.setData(res.getDate("data"));
			cr.setStatus(res.getString("status"));
			cr.setParcela(Integer.parseInt(res.getString("parcela")));
			cr.setId(res.getInt("id"));
			listaContasReceber.add(cr);
		}
		res.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	con.desconecta();
	
	return listaContasReceber;
}

}
