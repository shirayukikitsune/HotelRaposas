package br.com.hoteldasraposas.model.bi;

public class ReservationBySituation {
	private String situation;
	private Integer count;
	
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
