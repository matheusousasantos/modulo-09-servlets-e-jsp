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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		BeanCursoJsp usuario = new BeanCursoJsp();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		dao.salvar(usuario);
		
		try {
		RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
			request.setAttribute("usuarios", dao.listarTodos());
			view.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
