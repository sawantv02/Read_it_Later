package com.me.demo.pojo;

import java.util.HashSet; 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;



@Entity
@Table(name="usertable")
@PrimaryKeyJoinColumn(name="personID")
public class User extends Person {
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="roletype")
	private String roletype;

	//@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//@JoinColumn(name="content")
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy="user")
	@JsonManagedReference
	private Set<Content> content = new HashSet<Content>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="user")
	//@JoinColumn(name="message")
	@JsonManagedReference
	private Set<Message> messages = new HashSet<Message>();
	
	public User() {
		
	}
	/*public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}*/
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoletype() {
		return roletype;
	}
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
	
	public Set<Content> getContent() {
		return content;
	}
	public void setContent(Set<Content> content) {
		this.content = content;
	}
	
	
	
	}
	
