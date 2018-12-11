package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Produto;
import dao.ProdutoDAO;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProdutoDAO dao = new ProdutoDAO();

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String id = request.getParameter("id");

		try {

			if (acao.equalsIgnoreCase("listarTodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", dao.listar());
				view.forward(request, response);

			}

			if (acao.equalsIgnoreCase("deletar")) {
				dao.delete(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", dao.listar());
				view.forward(request, response);

			}

			if (acao.equalsIgnoreCase("editar")) {

				Produto p = dao.consultar(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("x", p);
				view.forward(request, response);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", dao.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.getStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			Produto p = new Produto();

			p.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			p.setNome(nome);
			p.setQuantidade(Double.parseDouble(quantidade));
			p.setValor(Double.parseDouble(valor));

			try {

				if (id.isEmpty() || id == null) {
					dao.salvar(p);

				} else if (!id.isEmpty() && id != null) {
					dao.atualizar(p);

				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", dao.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
