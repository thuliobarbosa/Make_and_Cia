package br.com.libertas.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.libertas.db.ProdutoDao;
import br.com.libertas.dto.Produto;

/**
 * Servlet implementation class AlterarProduto
 */
@WebServlet("/AlterarProduto")
public class AlterarProduto extends HttpServlet implements Modelo {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response){
		try {
			ProdutoDao pDao = new ProdutoDao();
			Produto p = new Produto();
			Gson gson = new Gson();
			
			p.setCodigo(request.getParameter("EdCodigo"));
			p.setDescricao(request.getParameter("EdDescricao"));
			p.setPreco_custo(Double.parseDouble(request.getParameter("EdPreco-custo")));
			p.setPreco_venda(Double.parseDouble(request.getParameter("EdPreco-venda")));
			p.setCategoria(Integer.parseInt(request.getParameter("EdCategoria")));
			p.setCod_fornecedor(Integer.parseInt(request.getParameter("EdFornecedor")));
			p.setQuantidade(Integer.parseInt(request.getParameter("EdQtd-estoque")));
			p.setId(Integer.parseInt(request.getParameter("ident")));
			
			PrintWriter out = response.getWriter();
			String res = gson.toJson(pDao.alterar(p));
			out.print(res);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
