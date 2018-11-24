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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
        
//		Refer�nte ao link clicado
		if(acao.equalsIgnoreCase("delete")) { 
			dao.delete(user);
			
			try {
			RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", dao.listarTodos());
				view.forward(request, response);
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		Refer�nte ao link clicado
//		Ao clicar os valores refer�ntes na tabela(o login) ser� inserido na consultar do dao e retorar� o registro refer�nte.
		else if(acao.equalsIgnoreCase("editar")) {
			
			BeanCursoJsp obj = dao.consultar(user);
//          Em reposta na pr�pia tela cadastro-usuario ser� inserido, vindo do BD(obj), no ID,LOGIN e SENHA do HTML
//			Como � uma consulta esse registro trar� no ID assim quando for salvo novamente s� ir� atualizar n�o add novamente.L72
			RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
			request.setAttribute("user",obj);
			view.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Se o id n�o existe ele vai salvar
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		BeanCursoJsp usuario = new BeanCursoJsp();
		
//		Passando o id
//		Se o id n�o estiver v�zio faz a convers�o sen�o adiciona 0.
		usuario.setId(!id.isEmpty()? Long.parseLong(id) : 0);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
//		Se o id for nulo ou vazio ele vai salvar
		if(id == null || id.isEmpty()) {
			dao.salvar(usuario);
		} else {
//		Se j� exite o id ele vai atualizar	
			dao.atualizar(usuario);
		}
		
		try {
//		Esse m�todo retorna todos os usuarios do BD inserindo em uma tabela <c:forEach items="${usuarios}" var="user">
		RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
			request.setAttribute("usuarios", dao.listarTodos());
			view.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
