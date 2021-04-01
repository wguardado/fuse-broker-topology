package com.imtj.fuse.broker.topology;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ServiceResponse")
public class ServiceResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String cxfMessage;
	
	private String amqMessage;

	public String getCxfMessage() {
		return cxfMessage;
	}

	public void setCxfMessage(String cxfMessage) {
		this.cxfMessage = cxfMessage;
	}

	public String getAmqMessage() {
		return amqMessage;
	}

	public void setAmqMessage(String amqMessage) {
		this.amqMessage = amqMessage;
	}
	
	
	
}
