package com.me.demo.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="contenttable")
public class Content {

	@Id
	@GeneratedValue
	@Column(name="contentID",unique=true,nullable=false)
	private long contentID;
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="user"))
	@JoinColumn(name="personID")
	@JsonBackReference
	private User user;
	
	@Transient
	private MultipartFile content_link;
	
	@Column(name="content_type")
	private String content_type;
	
	@Column(name="content_size")
	private long size;
	
	@Column(name="content_name")
	private String content_name;
	
	@Column(name="content_status")
	private String content_status;
	
	
	public MultipartFile getContent_link() {
		return content_link;
	}
	public void setContent_link(MultipartFile content_link) {
		this.content_link = content_link;
	}
	
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getContent_name() {
		return content_name;
	}
	public void setContent_name(String content_name) {
		this.content_name = content_name;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent_status() {
		return content_status;
	}
	public void setContent_status(String content_status) {
		this.content_status = content_status;
	}
	public long getContentID() {
		return contentID;
	}
	
}
