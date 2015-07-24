package br.com.hoteldasraposas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hoteldasraposas.dao.ReservationDAO;
import br.com.hoteldasraposas.model.Reservation;
import br.com.hoteldasraposas.model.User;

@Controller
@RequestMapping("/panel")
public class UserPanelController {
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String getProfile() {
		return "panel/profile";
	}
	
	@RequestMapping(value = "reservations", method = RequestMethod.GET)
	public String getReservations() {
		return "panel/reservations";
	}
	
	private final static int count = 10;
	
	@RequestMapping(value = "reservations", method = RequestMethod.POST)
	public @ResponseBody List<Reservation> postReservations(String orderBy, String order, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if (!order.equals("asc") && !order.equals("desc"))
			throw new SecurityException("Comando não permitido");
		if (!orderBy.equals("idreserva") && !orderBy.equals("data") && !orderBy.equals("situacao"))
			throw new SecurityException("Comando não permitido");
		
		ReservationDAO dao = new ReservationDAO();
		return dao.getReservationsByCpf(user.getCpf(), 0, count, orderBy, order); 
	}
}
