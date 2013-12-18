package com.ndc888.filter;

import java.util.List;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.TemplateLoader;

/**
 * Created with IntelliJ IDEA.
 * User: tianhuaichuan
 * Date: 13-1-8
 * Time: 下午2:16
 * To change this template use File | Settings | File Templates.
 */
public class CustomFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Override
    protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {

        return new HtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));

    }


}
