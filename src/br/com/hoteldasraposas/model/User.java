package br.com.hoteldasraposas.model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import br.com.hoteldasraposas.dao.UserDAO;
import br.com.hoteldasraposas.dao.UserTitleDAO;
import br.com.hoteldasraposas.util.json.CpfDeserializer;
import br.com.hoteldasraposas.util.json.CpfSerializer;

public class User {
	private Long cpf;
	private String email;
	private byte[] password;
	private Date createTime;
	private Integer userTitleId = 0;
	// Utiliza Lazy Loading nesta propriedade
	private UserTitle title = null;
	private String name;
	private String familyName;
	private Boolean admin;
	private Boolean active;
	
	@JsonSerialize(using = CpfSerializer.class)
	public Long getCpf() {
		return cpf;
	}
	@JsonDeserialize(using = CpfDeserializer.class)
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public byte[] getPassword() {
		return password;
	}
	@JsonIgnore
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public void setPlainPassword(String password) {
		UserDAO dao = new UserDAO();
		try {
			this.password = dao.encodePassword(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	@JsonSerialize(using = DateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	@JsonDeserialize(using = DateDeserializer.class)
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUserTitleId() {
		return userTitleId;
	}
	public void setUserTitleId(Integer userTitleId) {
		this.userTitleId = userTitleId;
		// Remove a informação utilizada no lazy loading
		title = null;
	}
	
	/**
	 * Retorna o {@link UserTitle} correspondente a este usuário ou null caso não exista.<br>
	 * Utiliza <i>lazy loading</i> (carrega a informação do banco de dados somente se for necessário).
	 */
	@JsonIgnore
	public UserTitle getTitle() {
		// Verifica se não foi carregada anteriormente
		if (title == null && userTitleId != 0) {
			UserTitleDAO titleDao = new UserTitleDAO();
			title = titleDao.getUserTitleById(userTitleId);
		}

		return title;
	}
	@JsonIgnore
	public void setTitle(UserTitle title) {
		this.title = title;
		this.userTitleId = title == null ? 0 : title.getId();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getFullName() {
		return getName() + " " + getFamilyName();
	}
}
