package br.com.hoteldasraposas.model;

public class RoomType {
	private Integer id;
	private Float dailyPrice;
	private String title;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getDaylePrice() {
		return dailyPrice;
	}
	public void setDailyPrice(Float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
