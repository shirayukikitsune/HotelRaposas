package br.com.hoteldasraposas.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hoteldasraposas.dao.BIDAO;
import br.com.hoteldasraposas.model.bi.Reservations;

@Controller
@RequestMapping("/admin/bi")
public class AdminBiController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getHome() {
		return "admin/bi/index";
	}
	
	@RequestMapping(value = "reservations", method = RequestMethod.POST)
	public @ResponseBody Reservations getReservationsDate(String inicio, String termino) {
		Date inicioDate, terminoDate;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			inicioDate = format.parse(inicio);
		}
		catch (Exception e) {
			inicioDate = null;
		}
		try {
			terminoDate = format.parse(termino);
		}
		catch (Exception e) {
			terminoDate = null;
		}
		
		BIDAO dao = new BIDAO();
		Reservations r = new Reservations();
		r.date = dao.getReservationHistory(inicioDate, terminoDate);
		r.situation = dao.getReservationsBySituation(inicioDate, terminoDate);
		return r;
	}
}
