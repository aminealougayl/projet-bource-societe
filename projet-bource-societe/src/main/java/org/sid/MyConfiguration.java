package org.sid;

import org.sid.rmi.ISocieteRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class MyConfiguration {

	@Autowired
	ISocieteRemote isr;
	
	@Bean
	public RmiServiceExporter rmiExporter(){
		RmiServiceExporter rmiExporter=new RmiServiceExporter();
		rmiExporter.setService(isr);
		rmiExporter.setServiceInterface(ISocieteRemote.class);
		rmiExporter.setServiceName("SC");
		return rmiExporter;
	}
}
