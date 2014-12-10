package com.zsq.chapter1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("s1")
public class Service1 {

	@GET
	@Path("/t1")
	@Produces("text/plain")
	@Consumes("text/plain")
	public String getHello(){
		return "hello";
	}
}
