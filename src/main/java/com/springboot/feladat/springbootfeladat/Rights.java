package com.springboot.feladat.springbootfeladat;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rights {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String code;
	private Date createdAt;
	private Date modifiedAt;
	
	@OneToMany(mappedBy="right")
	private List<User> users;
	
	protected Rights() {
		this.createdAt = new Date();
	}
	
	public Rights(Integer id, String name, String code) {		
		this.id = id;
		this.name = name;
		this.code = code;
		this.createdAt = new Date();
		this.modifiedAt = null;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	
	public Date getModifiedAt() {
		return modifiedAt;
	}


	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}


	@Override
	public String toString() {
		return "Rights [id=" + id + ", name=" + name + ", code=" + code + ", createdAt=" + createdAt + ", modifiedAt="
				+ modifiedAt + "]";
	}
	
	
	
	
	
}
