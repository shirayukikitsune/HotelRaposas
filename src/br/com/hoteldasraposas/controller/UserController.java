package br.com.hoteldasraposas.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hoteldasraposas.dao.UserDAO;
import br.com.hoteldasraposas.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	public class LoginResponse {
		private int status = 0;
		private String redirectUrl = null;
		private User user = null;
		
		public LoginResponse() {}
		public LoginResponse(int status) { setStatus(status); }

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getRedirectUrl() {
			return redirectUrl;
		}

		public void setRedirectUrl(String redirectUrl) {
			this.redirectUrl = redirectUrl;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="login")
	public @ResponseBody LoginResponse doLogin(String login, String password, HttpServletRequest request) {
		HttpSession session = request.getSession();

		UserDAO dao = new UserDAO();

		User user;
		try {
			byte[] encodedPassword = dao.encodePassword(password);
			user = dao.tryLogin(login, encodedPassword);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		session.setAttribute("user", user);
		
		LoginResponse response = new LoginResponse();
		
		response.setUser(user);
		response.setRedirectUrl(request.getParameter("redirect"));
		response.setStatus(user == null ? 1 : 0);

		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="logout")
	public @ResponseBody String doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		return request.getParameter("redirect");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="update")
	public @ResponseBody LoginResponse update(User update, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDAO dao = new UserDAO();
		
		User user = (User)session.getAttribute("user");
		if (user == null || update == null)
			return new LoginResponse(403);
		
		if (update.getName().length() < 3) 
			return new LoginResponse(1);
		if (update.getEmail().isEmpty())
			return new LoginResponse(2);
		if (!user.getEmail().equals(update.getEmail()) && dao.hasEmail(update.getEmail()))
			return new LoginResponse(3);
		
		user.setUserTitleId(update.getUserTitleId());
		user.setName(update.getName());
		user.setFamilyName(update.getFamilyName());
		if (update.getPassword() != null)
			user.setPassword(update.getPassword());
		user.setEmail(update.getEmail());
		
		// Validate the title
		if (user.getTitle() == null) {
			user.setTitle(null);
		}
		
		dao.update(user);
		
		LoginResponse response = new LoginResponse();
		response.setUser(user);
		response.setRedirectUrl(request.getParameter("redirect"));
		
		return response;
	}

	@RequestMapping(method=RequestMethod.POST, value="register")
	public @ResponseBody LoginResponse register(User newUser, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDAO dao = new UserDAO();
		
		if (newUser == null)
			return new LoginResponse(404);

		int status = 0;
		if (newUser.getName().length() < 2) 
			status |= 1;
		if (dao.hasEmail(newUser.getEmail()))
			status |= 2;
		if (newUser.getPassword() == null)
			status |= 4;
		if (dao.getUserByCpf(newUser.getCpf()) != null)
			status |= 8;
		if (status != 0)
			return new LoginResponse(status);

		// Validate the title
		if (newUser.getTitle() == null) {
			newUser.setTitle(null);
		}
		newUser.setAdmin(false);
		
		dao.insert(newUser);
		
		session.setAttribute("user", newUser);
		
		LoginResponse response = new LoginResponse();
		response.setUser(newUser);
		response.setRedirectUrl(request.getParameter("redirect"));
		
		return response;
	}
}
