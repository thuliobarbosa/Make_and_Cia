package br.com.libertas.model;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.libertas.db.ProdutoDao;

/**
 * Servlet implementation class ExcluiProduto
 */
@WebServlet("/ExcluiProduto")
public class ExcluiProduto extends HttpServlet implements Modelo {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluiProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProdutoDao pDao = new ProdutoDao();
			
			int id = Integer.parseInt(request.getParameter("identCad"));
			
			PrintWriter out = response.getWriter();
			String res = pDao.excluir(id);
			out.print(res);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	//}

}