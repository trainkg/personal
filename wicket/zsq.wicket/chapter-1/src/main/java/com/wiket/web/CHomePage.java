package com.wiket.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class CHomePage extends WebPage {
	
	public CHomePage() {
		add(new Label("message","this is my message"));
	}
}
