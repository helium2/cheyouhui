/**
 * 
 */
package com.ndc888.filter;

import java.io.IOException;

import org.sitemesh.DecoratorSelector;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.Content;
import org.sitemesh.webapp.WebAppContext;

/**
 * @title 		
 * @description	
 * @usage		
 * @company		上海微汇信息技术有限公司
 * @author		TQSUMMER
 * @create		2012-8-28 下午5:04:26
 */
/**
 * @author TQSUMMER
 */
public class SiteMeshMetaFilter extends ConfigurableSiteMeshFilter {
	private final String DECORATOR_PREFIX = "/";
	private final String DECORATOR_SUFFIX = ".html";

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

		super.applyCustomConfiguration(builder);

		builder.setCustomDecoratorSelector(new DecoratorSelector<WebAppContext>() {

			@Override
			public String[] selectDecoratorPaths(Content content,
					WebAppContext context) throws IOException {

				String decorator = content.getExtractedProperties()
						.getChild("meta").getChild("layout").getValue();
				String[] selectDecoratorPath = null;
				if (decorator == null || decorator.trim().equals("")) {
					selectDecoratorPath = new String[] {};
				} else {
					selectDecoratorPath = new String[] { DECORATOR_PREFIX
							+ decorator.trim() + DECORATOR_SUFFIX };
				}

				return selectDecoratorPath;

			}

		});

	}
}
