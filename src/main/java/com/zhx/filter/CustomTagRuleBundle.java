package com.zhx.filter;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

public class CustomTagRuleBundle implements TagRuleBundle {

	@Override
	public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
		defaultState.addRule("myCss", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("myCss"), false));  
	}

	@Override
	public void install(State arg0, ContentProperty arg1, SiteMeshContext arg2) {
		
	}
	
}
