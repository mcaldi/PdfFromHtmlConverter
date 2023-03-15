package mcdev.pdfFromHtml.freemarker;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import mcdev.pdfFromHtml.App;
import mcdev.pdfFromHtml.freemarker.model.UsersDTO;

public class FreemarkerParser {

	public String parseTemplate(String inputTemplatePath, boolean useExternalPath, Map<String, UsersDTO> model) throws Exception {
        Configuration cfg = new Configuration(new Version("2.3.23"));
    	cfg.setClassForTemplateLoading(App.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        
        Template template=null;
        
        if(useExternalPath) {
        	File templ = new File(inputTemplatePath);
        	System.out.println("Path: " + new File(inputTemplatePath).getParent() + " Name: " + new File(inputTemplatePath).getName());
        	FileTemplateLoader templateLoader = new FileTemplateLoader(new File(templ.getParent()));
        	cfg.setTemplateLoader(templateLoader);
        	template=cfg.getTemplate(templ.getName());
        } else {
        	template=cfg.getTemplate(inputTemplatePath);
        }
        
        try (StringWriter out = new StringWriter()) {
        	template.process(model, out);
            System.out.println(out.getBuffer().toString());
            return out.getBuffer().toString();
        } catch(Exception e) {
        	System.err.println("Error during freemarker parsing: " + e);
        	throw e;
        }
        
	}
	
}
