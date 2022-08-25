package com.dbs.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	private String messageCode;
	private String instruction;
	
	public Message(String messageCode, String instruction) {
		super();
		this.messageCode = messageCode;
		this.instruction = instruction;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	@Override
	public String toString() {
		return "Message [messageCode=" + messageCode + ", instruction=" + instruction + "]";
	}
	
	
}
