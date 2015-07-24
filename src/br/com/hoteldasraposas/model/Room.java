package br.com.hoteldasraposas.model;

import br.com.hoteldasraposas.dao.RoomTypeDAO;

public class Room {
	private Integer id;
	private Integer typeId = 0;
	private RoomType type = null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
		this.type = null;
	}
	public RoomType getType() {
		if (typeId != 0 && type == null) {
			RoomTypeDAO dao = new RoomTypeDAO();
			type = dao.getTypeById(typeId);
		}
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
		this.typeId = type.getId();
	}
}
