package com.zsq.chapter1;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

//@Controller
//@Path(GroupResource.CONTACTS_URL)
public class GroupResource {

	public static final String CONTACTS_URL = "/contacts";

	@Autowired
	private ContactService service;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("data")
	public Contacts getAll() {
		return service.getAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("data1")
	public Map<String, String> getGroupInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("t1", "test");
		return map;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("wlc")
	public String getWelcome() {
		return "Welcome, ";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public ModelAndView viewAll() {
		return new ModelAndView("contacts", "contacts", service.getAll());
	}
	
	
}
