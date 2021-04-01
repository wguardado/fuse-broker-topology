package com.imtj.fuse.broker.topology;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/main")
public interface MainService {

	@GET
    @Path("/service")
	@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "The Main Service")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Response from the Service", response=ServiceResponse.class),
    		@ApiResponse(code = 500, message = "Service Error"),})
	public ServiceResponse mainService();
	
	
	
}
