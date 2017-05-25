package com.me.demo.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;


@Entity
@Table(name="messagetable")
public class Message {

	@Id
	@GeneratedValue
	@Column(name="messageID",unique=true,nullable=false)
	private long messageID;

	@Column(name="receiver")
	private String receiver;
	
	@Column(name="messageBody")
	private String messageBody;
	
	@Column(name="messageDate")
	private Date messageDate;
	
	@Column(name="sender")
	private String sender;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	//@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="user"))
	@JoinColumn(name="personID")
	@JsonBackReference
	private User user;
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
	
	
	
}
