package com.imtj.fuse.broker.topology;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MainRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		onException(Exception.class)
			.routeId("service.exception.handler")
			.handled(true)
			.log("There is an Error on the Routes: ${exception.message}")
			.process(exchange -> {
					Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
					ServiceResponse resp = exchange.getProperty("serviceResponse", ServiceResponse.class);
					resp.setAmqMessage("NO MESSAGE FROM AMQ!!! WE GOT AN ERROR " + (ex!=null ? ex.getMessage() : "") );
					exchange.getIn().setBody(resp);
			});
	
		
		from("activemq:service.request")
			.routeId("service.request.activemq")
			.log("ACTIVEMQ Received request")
			.setBody(constant("HELLO FROM ACTIVEMQ"))
			.log("ACTIVEMQ Message response: ${body}");
		
			
		from("cxfrs:bean:cxfRsServer")
			.routeId("service.cxf")
			.process(exchange -> {
				ServiceResponse resp = new ServiceResponse();
				resp.setCxfMessage("HELLO FROM CXF!");
				exchange.setProperty("serviceResponse", resp);
			})
			.inOut("activemq:service.request?replyTo=service.response&replyToType=Shared")
			.log("Response from ActiveMQ: ${body}")
			.process(exchange -> {
					String body = exchange.getIn().getBody(String.class);
					ServiceResponse resp = exchange.getProperty("serviceResponse", ServiceResponse.class);
					resp.setAmqMessage(body);
					exchange.getIn().setBody(resp);
				
			})
			;
		
		
	}

}
