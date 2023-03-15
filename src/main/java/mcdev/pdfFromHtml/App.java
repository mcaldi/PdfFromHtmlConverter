package mcdev.pdfFromHtml;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import mcdev.pdfFromHtml.freemarker.FreemarkerParser;
import mcdev.pdfFromHtml.freemarker.model.ModelFaker;
import mcdev.pdfFromHtml.freemarker.model.UsersDTO;
import mcdev.pdfFromHtml.pdfMaker.IMakerPdf;
import mcdev.pdfFromHtml.pdfMaker.PDFLibraryEnum;
import mcdev.pdfFromHtml.pdfMaker.impl.FyingsaucerMakerPdf;
import mcdev.pdfFromHtml.pdfMaker.impl.OpenHtmlToPdfMakerPdf;

public class App 
{
	
	
	private static final String OUTPUT_FILE = "/media/sf_shared/output.pdf";
	private static final String INPUT_FILE = "/index.ftl";
	private static final String STATIC_FOLDER_URI = "/static/";
	private static final boolean USE_EXTERNAL_PATH = false;

	private static final boolean CREATE_PDFA = false;
	private static final boolean CREATE_PDF_ACCESSIBLE = false;
	private static final int CONVERSION_LIB = 1;
	

    public static void main( String[] args ) throws Exception
    {
    	
    	String templatePath = INPUT_FILE;
    	String outPdfPath = OUTPUT_FILE;
    	int conversionMode = CONVERSION_LIB;
    	String staticFolderUri = STATIC_FOLDER_URI;
    	boolean useExternalPath = USE_EXTERNAL_PATH;
    	boolean createPdfa = CREATE_PDFA;
    	boolean createAccessiblePdf = CREATE_PDF_ACCESSIBLE;
    	
    	Options opts = cliOptionCreation() ;
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helper = new HelpFormatter();
        
        try {
            CommandLine cmdl = parser.parse(opts, args);
            
            if(cmdl.hasOption("l")) {
            	System.out.println(">> " + cmdl.getOptionValue("l"));
            	conversionMode = Integer.parseInt(cmdl.getOptionValue("l"));
            	System.out.println("conversionMode set to: " + conversionMode);
            } else
            	System.out.println("Option l/lib is not found. Using default value: " + conversionMode);
            
            if(cmdl.hasOption("h")) {
                HelpFormatter fmt = new HelpFormatter();
                fmt.printHelp("Help", opts);
                System.exit(0);
            } 
            

            if(cmdl.hasOption("i")) {
            	templatePath = cmdl.getOptionValue("i");
            	System.out.println("Input templatePath set to: " + templatePath);
            } else
            	System.out.println("Option in/input is not found. Using default value: " + templatePath);
            
            if(cmdl.hasOption("o")) {
            	outPdfPath = cmdl.getOptionValue("o");
            	System.out.println("Output PDF set to: " + outPdfPath);
        	}else
            	System.out.println("Option o/out is not found. Using default value: " + outPdfPath);
            
            if(cmdl.hasOption("e")) {
            	useExternalPath = true;
            	System.out.println("Using External Paths for Input, Output and Assets folders - Please use absolutes paths");
            } else
            	System.out.println("Option e/external is not found. Using default value: " + useExternalPath);
            
            if(cmdl.hasOption("a")) {
            	if(!useExternalPath) {
            		staticFolderUri = App.class.getResource(cmdl.getOptionValue("a")).getPath();
            	} else {
            		staticFolderUri = cmdl.getOptionValue("a");
            	}
            } else {
            	if(!useExternalPath) {
            		staticFolderUri = App.class.getResource(staticFolderUri).getPath();
            	} 
            	System.out.println("Option a/assets is not found. Using default value: " + staticFolderUri);
            }
            
            if(cmdl.hasOption("x")) {
            	createPdfa = true;
            	System.out.println("Using PDF/A Creation");
            } else 
            	System.out.println("Option pa/pdfa is not found. Using default value: " + createPdfa);
            
            if(cmdl.hasOption("u")) {
            	createAccessiblePdf = true;
            	System.out.println("Generate accessibile PDF");
            } else
            	System.out.println("Option ac/accessible is not found. Using default value: " + createAccessiblePdf);
        }
        catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            helper.printHelp("Usage:", opts);
            throw exp;
        }
	
        
        UsersDTO reportData =  ModelFaker.getUserData();
      	Map<String, UsersDTO> model = new HashMap<String, UsersDTO>();
      	model.put("data", reportData);
      	
      	FreemarkerParser fp = new FreemarkerParser();
      	String html = fp.parseTemplate(templatePath, useExternalPath, model);
      	
      	
      	HashMap<Integer,IMakerPdf> creationStrategy = new HashMap<Integer,IMakerPdf>();
      	creationStrategy.put(PDFLibraryEnum.OPENHTMLTOPDF.getValue(), new OpenHtmlToPdfMakerPdf());
      	creationStrategy.put(PDFLibraryEnum.FYING_SAUCER.getValue(), new FyingsaucerMakerPdf());


        creationStrategy.get(conversionMode).createPdf(html, staticFolderUri, createPdfa, createAccessiblePdf, outPdfPath);
        System.out.println("PDF Created : " + outPdfPath);
        
        System.out.println("END");
    }
   
    
    private static Options cliOptionCreation() {
    	// create Options object
    	Options options = new Options();
    	options.addOption("h", "help", false, "help");
    	options.addOption("l", "lib", true, "conversion library - 0 or 1");
    	options.addOption("i", "in", true, "input file absolute path");
    	options.addOption("o", "out", true, "output file absolute path");
    	options.addOption("a", "assets", true, "assets folder absolute path");
    	options.addOption("e", "external", false, "if present external path used, false otherwise. Default value false");
    	options.addOption("x", "pdfa", false, "if present, pdfa generation is required, false otherwise. Valid only for lib = 0. Default value false");
    	options.addOption("u", "accessibility", false, "if present, an accessible pdf  is required, false otherwise. Valid only for lib = 0. Default value false");
    	return options;
    }

    

    
}
