package com.libertymutual.goforcode.spark.app.utilities;

import java.util.Map;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import spark.ModelAndView;
import spark.TemplateEngine;

public class JTwigRenderer extends TemplateEngine {
	
    private String templatesDirectory;

    public JTwigRenderer() {
        this.templatesDirectory = "templates";
    }

    public JTwigRenderer(String customTemplatesDirectory) {
        this.templatesDirectory = customTemplatesDirectory;
    }

    
    public String render(ModelAndView modelAndView) {
        String viewName = templatesDirectory + "/" + modelAndView.getViewName();
        JtwigTemplate template = JtwigTemplate.classpathTemplate(viewName);
        JtwigModel model = JtwigModel.newModel((Map<String, Object>)modelAndView.getModel());
        return template.render(model);
    }

}
