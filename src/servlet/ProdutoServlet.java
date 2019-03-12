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

			try {

				String msg = null;
				boolean podeInserir = true;

				if (valor == null || valor.isEmpty()) {
					msg = "Valor R$ deve ser informado";
					podeInserir = false;

				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "Quantidade deve ser informado";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {
					podeInserir = false;

				}

				Produto produto = new Produto();
				produto.setNome(nome);
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);

				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}

				if (valor != null && !valor.isEmpty()) {
					
					String arrumandoPonto = valor.replaceAll("\\.", "");
					String arrumandoVirgula = arrumandoPonto.replaceAll("\\,", ".");
					produto.setValor(Double.parseDouble(arrumandoVirgula));
				}
				
				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && daoProduto.validarNome(nome) && podeInserir) {

					daoProduto.salvar(produto);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoProduto.atualizar(produto);
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
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
