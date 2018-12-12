package servlet;

import java.io.IOException;

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

	ProdutoDAO daoProduto = new ProdutoDAO();

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String id = request.getParameter("id");

			if (acao.equalsIgnoreCase("deletar")) {
				daoProduto.delete(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				Produto p = daoProduto.consultar(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("x", p);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro no doGet");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
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

				String msg = null;
				boolean podeInserir = true;
				// Se o produto não tiver cadastrado e o nome já existe
				if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {
					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				if (id == null || id.isEmpty() && daoProduto.validarNome(nome) && podeInserir) {

					daoProduto.salvar(p);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoProduto.atualizar(p);
				}

				if (!podeInserir) {
					request.setAttribute("x", p);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
