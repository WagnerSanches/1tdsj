package br.com.fiap.estacionamento.control.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.estacionamento.model.business.UsuarioBO;
import br.com.fiap.estacionamento.model.entities.Usuario;

public class Login implements Tarefa {

	@Override
	public String processarRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new UsuarioBO().validarLogin(email, senha);
		
		if(usuario != null) {
			HttpSession session = req.getSession();
			session.setAttribute("usuario", usuario);
			return "valet.jsp";
		} else {
			req.setAttribute("erro", "Usuário e/ou Senha não existem.");
			return "erro.jsp";
		}
		
	}
}
