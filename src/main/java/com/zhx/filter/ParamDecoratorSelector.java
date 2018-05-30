package com.zhx.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.sitemesh.DecoratorSelector;
import org.sitemesh.content.Content;
import org.sitemesh.webapp.WebAppContext;

public class ParamDecoratorSelector implements DecoratorSelector<WebAppContext>{
	
	private DecoratorSelector<WebAppContext> defaultDecoratorSelector;

    public ParamDecoratorSelector(DecoratorSelector<WebAppContext> defaultDecoratorSelector) {
        this.defaultDecoratorSelector = defaultDecoratorSelector;
    }

	@Override
	public String[] selectDecoratorPaths(Content content, WebAppContext context)
			throws IOException {
		// build decorator based on the request
        HttpServletRequest request = context.getRequest();
        String pjax = request.getParameter("_pjax");//如果是pjax请求调用空白模板
        if (StringUtils.isNotBlank(pjax)) {
        	//使用blank模板
            return new String[] { "/WEB-INF/views/layouts/blank.jsp" };
        }
        return defaultDecoratorSelector.selectDecoratorPaths(content, context);
	}

}
