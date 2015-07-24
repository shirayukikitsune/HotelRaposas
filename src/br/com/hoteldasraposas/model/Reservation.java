package br.com.hoteldasraposas.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.hoteldasraposas.dao.ReservationSituationDAO;
import br.com.hoteldasraposas.dao.UserDAO;

public class Reservation {
	private Integer id;
	private Long cpf = 0L;
	private User user = null;
	private Date date;
	private Integer situationId = 0;
	private ReservationSituation situation = null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
		this.user = null;
	}
	@JsonIgnore
	public User getUser() {
		if (user == null && cpf != 0L) {
			UserDAO dao = new UserDAO();
			user = dao.getUserByCpf(cpf);
		}
		
		return user;
	}
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
		this.cpf = user != null ? user.getCpf() : 0L;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getSituationId() {
		return situationId;
	}
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}
	public ReservationSituation getSituation() {
		if (situation == null && situationId != 0) {
			ReservationSituationDAO dao = new ReservationSituationDAO();
			situation = dao.getReservationSituationById(situationId);
		}
		
		return situation;
	}
	public void setSituation(ReservationSituation situation) {
		this.situation = situation;
		this.situationId = situation != null ? situation.getId() : 0;
	}
}
