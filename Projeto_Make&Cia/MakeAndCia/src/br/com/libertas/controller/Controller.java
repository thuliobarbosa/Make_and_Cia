package br.com.libertas.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.libertas.model.Modelo;

/**
 * Servlet implementation class CadastroProduto
 */
@WebServlet("/Controller")

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String service = request.getParameter("service");
			
			if (service == null) {
				service = ""; 
			}
			
			String nomeDaClasse = "br.com.libertas.model." + service;
			Class classe = Class.forName(nomeDaClasse);
			Modelo modelo = (Modelo) classe.newInstance();
			modelo.execute(request, response);
			
		} catch (Exception e) {
			PrintWriter pw = response.getWriter();
			pw.print("Invalid parameter!");
			e.printStackTrace();
		}

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
