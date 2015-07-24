package br.com.hoteldasraposas.filters;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.hoteldasraposas.dao.ReservationRoomDAO;

@WebFilter("/admin/reservations")
public class AdminReservationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, 
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;

		ReservationRoomDAO dao = new ReservationRoomDAO();

		Date inicio = null, termino = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			inicio = format.parse(request.getParameter("inicio"));
			termino = format.parse(request.getParameter("termino"));
			
			if (inicio.compareTo(termino) > 0) {
				inicio = termino;
			}
		}
		catch (Exception e) {
			inicio = termino = new Date();
		}
		
		req.setAttribute("inicio", format.format(inicio));
		req.setAttribute("termino", format.format(termino));
		req.setAttribute("lista", dao.getListByPeriod(inicio, termino));	
		
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
