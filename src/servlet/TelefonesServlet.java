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
import beans.Telefone;
import dao.TelefoneDAO;
import dao.UsuarioDAO;

@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private TelefoneDAO telefoneDAO = new TelefoneDAO();
	

	public TelefonesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("addFone")) {
				
				String user = request.getParameter("user");
				
				BeanCursoJsp u = usuarioDAO.consultar(user);
				request.getSession().setAttribute("usuarioEscolhido", u);
				request.setAttribute("usuarioEscolhido", u); //Precisa pois será requisitado na tela telefones
				
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", telefoneDAO.listar(u.getId()));
				request.setAttribute("msg", "Salvo com sucesso!");
				view.forward(request, response);
				
			} else 
			if(acao.equalsIgnoreCase("deleteFone")) {
				
				String FoneId = request.getParameter("foneId");	
				telefoneDAO.delete(FoneId);	
				
				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("usuarioEscolhido");
				
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", telefoneDAO.listar(beanCursoJsp.getId()));
				request.setAttribute("msg", "Removido com sucesso!");
				view.forward(request, response);
			}		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		try {
		
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("usuarioEscolhido");
			
			if(acao != null && acao.equalsIgnoreCase("voltar")) {
				
				System.out.println("caiu aqui!! voltar");
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", usuarioDAO.listar());
				view.forward(request, response);
				
			}else{
				
				System.out.println("caiu aqui!! fluxo normal");
				String numero = request.getParameter("numero");
				String tipo = request.getParameter("tipo");
				
				Telefone t = new Telefone();
				t.setNumero(numero);
				t.setTipo(tipo);
				t.setUsuario(beanCursoJsp.getId());
				
				telefoneDAO.salvar(t);
				
				request.getSession().setAttribute("usuarioEscolhido", beanCursoJsp);
				request.setAttribute("usuarioEscolhido", beanCursoJsp);
				
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", telefoneDAO.listar(beanCursoJsp.getId()));
				view.forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
