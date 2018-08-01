package com.springboot.feladat.springbootfeladat;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Date createdAt;
	private Date modifiedAt;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Rights right;

	@OneToMany(mappedBy="user")
	private List<Task> tasks;
	
	protected User() {
		this.createdAt = new Date();
	}
	
	public User(int id, String name, String password) {		
		this.id = id;
		this.name = name;
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
	public Date getCreatedAt() {
		return createdAt;
	}	
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
	
	

}
