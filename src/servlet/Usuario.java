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

	UsuarioDAO daoUsuario = new UsuarioDAO();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp obj = daoUsuario.consultar(user);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("user", obj);
				view.forward(request, response);
				
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			// Se o id n�o existe ele vai salvar
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");

			BeanCursoJsp usuario = new BeanCursoJsp();
			
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);
			
			try {

				String msg = null;
				boolean podeInserir = true;
				
//				** VALIDA��O DE CAMPOS:
//				Se algum desse campos estiver vazio n�o faz nada e mostra a mensagem de erro na tela {
				if(login == null || login.isEmpty()) {
					msg = "Login deve ser informado!";
					podeInserir = false;
				} 
				
				else if(senha == null || senha.isEmpty()) {
					msg = "Senha deve ser informado!";
					podeInserir = false;
				}
				
				else if(nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado!";
					podeInserir = false;
				}
				
				else if(telefone == null || telefone.isEmpty()) {
					msg = "Telefone deve ser informado!";
					podeInserir = false;
				}
				
//			}
				
//			    Sen�o faz a valida��o normal				
				else if(id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					msg = "Login j� cadastrado!";
					podeInserir = false;
				}
				
//				Quando o usu�rio � novo e usa um login j� cadastrado			
				else if(id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "Senha j� cadastrada!";
					podeInserir = false;
				}
				
//				Fim da estrutura de valida��o...
				
//				Se alguma das anteriores for verdade mostra a mensagem na tela
				if(msg != null) {
					request.setAttribute("msg", msg);
				}
				
//				Se for mostrado alguma mensagem de erro na tela quer dizer que voce n�o pode salvar
//				nem atualizar
				else if(id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.salvar(usuario);
					
					
				} 
				
				else if(id != null && !id.isEmpty() && podeInserir) {
					daoUsuario.atualizar(usuario);
					System.out.println("Atualizado");
				}
				
			if(!podeInserir) {
					request.setAttribute("user", usuario);
				}
				
				RequestDispatcher view = request.getRequestDispatcher("cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msg", "Salvo com sucesso!");
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
