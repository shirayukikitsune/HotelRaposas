package br.com.hoteldasraposas.model;

import java.util.Date;

import br.com.hoteldasraposas.dao.RoomDAO;
import br.com.hoteldasraposas.dao.ReservationDAO;

public class ReservationRoom {
	private Integer reservationId = 0;
	private Reservation reservation = null;
	private Integer roomId = 0;
	private Room room = null;
	private Date start;
	private Date end;
	private Float dailyPrice;
	
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
		this.reservation = null;
	}
	public Reservation getReservation() {
		if (reservation == null && reservationId != 0) {
			ReservationDAO dao = new ReservationDAO();
			reservation = dao.getReservationById(reservationId);
		}
		
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
		// Sem verificação por nulo, Reserva é chave primária, logo se for nulo, será lançada uma NullPointerException
		this.reservationId = reservation.getId();
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
		this.room = null;
	}
	public Room getRoom() {
		if (room == null && roomId != 0) {
			RoomDAO dao = new RoomDAO();
			room = dao.getRoomById(roomId);
		}
		
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
		// Sem verificação por nulo, Quarto é chave primária, logo se for nulo, será lançada uma NullPointerException
		this.roomId = room.getId();
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Float getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(Float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
}
