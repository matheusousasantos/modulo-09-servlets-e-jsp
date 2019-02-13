package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.tomcat.util.codec.binary.Base64;

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

			// Se o id não existe ele vai salvar
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
				
				/* File upload de imagens e pdf */
				
				if(ServletFileUpload.isMultipartContent(request)) {
					
					List<FileItem> fileIntens = 
							new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					
					for (FileItem fileItem : fileIntens) {
						if(fileItem.getFieldName().equals("foto")) {
							String foto = new Base64().encodeBase64String(fileItem.get());
							System.out.println();
						}
					}
					
				}

				String msg = null;
				boolean podeInserir = true;
				
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
				
			
				else if(id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					msg = "Login já cadastrado!";
					podeInserir = false;
				}
						
				else if(id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "Senha já cadastrada!";
					podeInserir = false;
				}

				if(msg != null) {
					request.setAttribute("msg", msg);
				}
				

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
