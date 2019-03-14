package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.UsuarioDAO;

@WebServlet("/salvarUsuario")
@MultipartConfig
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

			if (acao != null && acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp obj = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("user", obj);
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("download")) {

				BeanCursoJsp obj = daoUsuario.consultar(user);

				if (obj != null) {

					String contentType = "";
					byte[] fileBytes = null;

					String tipo = request.getParameter("tipo");

					if (tipo.equalsIgnoreCase("imagem")) {

						contentType = obj.getContentType();
						fileBytes = new Base64().decodeBase64(obj.getFotoBase64());
					}

					else if (tipo.equalsIgnoreCase("curriculo")) {

						contentType = obj.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(obj.getCurriculoBase64());
					}

					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					InputStream is = new ByteArrayInputStream(fileBytes);
					int read = 0;

					byte[] bytes = new byte[1024];

					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {

						os.write(bytes, 0, read);
					}

					os.flush();
					os.close();
				}
			} else {
				
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

			System.out.println("entrou aqui! - doPost Atualizar");

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

				if (ServletFileUpload.isMultipartContent(request)) {

					Part imagemFoto = request.getPart("foto");

					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {

						String fotoBase64 = new Base64().encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));

						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());

					} else {

						usuario.setFotoBase64(request.getParameter("fotoTemp")); // Já vem em Base64 pois vem do BD.
																					// pode ser setado direto.
						usuario.setContentType(request.getParameter("contentTypeTemp")); // Faltou o conentType

					}

					// Adiciona a pdf que vem de parte(PART) do formulário
					Part curriculoPdf = request.getPart("curriculo");

					if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {

						String curriculoBase64 = new Base64().encodeBase64String(converteStreamParaByte(curriculoPdf.getInputStream()));

						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(curriculoPdf.getContentType());

					} else {

						usuario.setCurriculoBase64(request.getParameter("fotoTempPDF"));
						usuario.setContentTypeCurriculo(request.getParameter("contentTypeTempPDF"));

					}
				}

				String msg = null;
				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					msg = "Login deve ser informado!";
					podeInserir = false;
				}

				else if (senha == null || senha.isEmpty()) {
					msg = "Senha deve ser informado!";
					podeInserir = false;
				}

				else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado!";
					podeInserir = false;
				}

				else if (telefone == null || telefone.isEmpty()) {
					msg = "Telefone deve ser informado!";
					podeInserir = false;
				}

				else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					msg = "Login já cadastrado!";
					podeInserir = false;
				}

				else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "Senha já cadastrada!";
					podeInserir = false;
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.salvar(usuario);

				}

				else if (id != null && !id.isEmpty() && podeInserir) {
					daoUsuario.atualizar(usuario);
					System.out.println("Atualizado");
				}

				if (!podeInserir) {
					request.setAttribute("user", usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msg", "Salvo com sucesso!");
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro fora da verificação");
			}

		}
	}

	/*
	 * Converte a entrada de fluxo de dados da imagem para um array de byte(byte[])
	 */
	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int reads = imagem.read();

		while (reads != -1) {
			baos.write(reads); /* Será escrito um fluxo de saída gravando em uma matriz de bytes */
			reads = imagem.read();
		}

		return baos.toByteArray();

	}

}
