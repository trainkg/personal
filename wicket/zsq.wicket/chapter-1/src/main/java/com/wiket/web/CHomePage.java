package com.wiket.web;

import org.apache.wicket.core.request.mapper.HomePageMapper;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class CHomePage extends WebPage {
	
	public CHomePage() {
		add(new Label("1","label"));
	}
}
