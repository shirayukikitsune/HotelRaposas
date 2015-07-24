package br.com.hoteldasraposas.acao.reserva;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.hoteldasraposas.acao.Acao;
import br.com.hoteldasraposas.dao.RoomTypeDAO;

public class SelecionarQuarto implements Acao {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		HttpSession session = req.getSession();
		
		RoomTypeDAO dao = new RoomTypeDAO();
		
		String tipoParam = req.getParameter("tipo");
		
		int tipoId = Integer.parseInt(tipoParam);
		
		session.setAttribute("reservaTipoQuarto", dao.getTypeById(tipoId));
		
		return "/reserva.jsp";
	}

}
