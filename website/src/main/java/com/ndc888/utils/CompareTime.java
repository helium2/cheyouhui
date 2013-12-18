package com.ndc888.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.SimpleDate;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


public class CompareTime implements TemplateDirectiveModel{

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        Date date = new Date();
        String showStr = "";
        try {
            SimpleDate cdate = (SimpleDate) params.get("cdate");

            if(date.compareTo(cdate.getAsDate()) == 0) {
                showStr = params.get("eStr").toString();
            } else if (date.compareTo(cdate.getAsDate()) > 0) {
                showStr = params.get("lStr").toString();  // 未来
            } else if (date.compareTo(cdate.getAsDate()) < 0) {
                showStr = params.get("fStr").toString();  // 过去
            }
            env.getOut().write(showStr);
        } catch (RuntimeException e) {
            env.getOut().write(showStr);
        }
    }
}
