package br.com.hoteldasraposas.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.hoteldasraposas.dao.UserDAO;
import br.com.hoteldasraposas.model.User;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest))
			throw new SecurityException("Invalid request");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		if (session.getAttribute("user") == null)
			throw new SecurityException("Efetue login primeiro");
		
		User usuario = (User)session.getAttribute("user");
		if (usuario.getAdmin() == false)
			throw new SecurityException("Usuário sem permissão");
		
		UserDAO dao = new UserDAO();
		request.setAttribute("userCount", dao.getUserCount());
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
