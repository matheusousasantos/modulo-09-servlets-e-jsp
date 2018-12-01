package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.UsuarioDAO;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UsuarioDAO dao = new UsuarioDAO();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		try {

			if (acao.equalsIgnoreCase("delete")) {
				dao.delete(user);

				System.out.println("Deletado");
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp obj = dao.consultar(user);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("user", obj);
				view.forward(request, response);
				
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		System.out.println(acao);
		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			// Se o id não existe ele vai salvar
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			
			try {
//				pra eu poder validar precisa:
//				O id precisa ser nulo ou vazio(criando novo)
//				e se já existe usuário cadastrado com aquele login
				if(id == null || id.isEmpty() && !dao.validarLogin(login)) {
					request.setAttribute("msgLogin", "Login já cadastrado!");
					System.out.println("Erro! Login - Já cadastrado");
				}
				
				if(id == null || id.isEmpty() && !dao.validarSenha(senha)) {
					request.setAttribute("msgSenha", "Senha já cadastrado!");
					System.out.println("Erro! Senha - Já cadastrado");
				}
			
				if ((id == null || id.isEmpty()) && (dao.validarLogin(login) && dao.validarSenha(senha))) {
					dao.salvar(usuario);
					System.out.println("Salvando");
					
				} else if(id != null && !id.isEmpty()){
					dao.atualizar(usuario);
					System.out.println("Atualizando");
				} 

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
