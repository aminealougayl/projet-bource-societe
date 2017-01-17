package org.sid.service;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class SendAMQPService {

	private  String QUEUE_NAME = "societe_queue";
	private  String  message = "New Societe!";

	public void send() throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	      
	    channel.close();
	    connection.close();
	}
	
	public String getQUEUE_NAME() {
		return QUEUE_NAME;
	}

	public void setQUEUE_NAME(String qUEUE_NAME) {
		QUEUE_NAME = qUEUE_NAME;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
