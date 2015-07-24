package br.com.hoteldasraposas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hoteldasraposas.dao.UserDAO;
import br.com.hoteldasraposas.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String redirectHome() {
		return "redirect:/admin/home";
	}
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String getHome() {
		return "admin/index";
	}
	
	@RequestMapping(value = "reservations", method = RequestMethod.GET)
	public String getReservations() {
		return "admin/reservations";
	}
	
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String getUsers() {
		return "admin/users";
	}
	
	@RequestMapping(value = "users/list", method = RequestMethod.POST)
	public @ResponseBody List<User> postUsersList() {
		UserDAO dao = new UserDAO();
		return dao.getList();
	}
}
