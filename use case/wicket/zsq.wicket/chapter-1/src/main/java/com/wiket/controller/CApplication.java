package com.wiket.controller;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wiket.web.CHomePage;

/**
 * 
 * @author Administrator
 */
public class CApplication extends WebApplication {
	private Logger log = LoggerFactory.getLogger(getClass());
	

	@Override
	public Class<? extends Page> getHomePage() {
		return CHomePage.class;
	}	

}
